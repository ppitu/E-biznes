package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.sql.Timestamp
import java.time.Instant
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class OrderRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, userRepository: UserRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class OrderTable(tag: Tag) extends Table[Order](tag, "order") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def user_id = column[Long]("user_id")

    def amount = column[Float]("amount")

    def date = column[String]("date")

    def user_fk = foreignKey("user_fk", user_id, user_)(_.id)

    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")

    def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")

    def * = (id, user_id, amount, date, createdAt, updatedAt) <> ((Order.apply _).tupled, Order.unapply)
  }

  import userRepository.UserTable

  private val user_ = TableQuery[UserTable]
  private val order_ = TableQuery[OrderTable]

  def create(user_id: Long, amount: Float, date: String, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now())): Future[Order] = db.run {
    (order_.map(o => (o.user_id, o.amount, o.date, o.createdAt, o.updatedAt))
      returning order_.map(_.id)
      into {case ((user_id, amount, date, createdAt, updatedAt), id) => Order(id, user_id, amount, date, createdAt, updatedAt)}
      ) += (user_id, amount, date, createdAt, updatedAt)
  }

  def list(): Future[Seq[Order]] = db.run {
    order_.result
  }

  def getById(id: Long): Future[Order] = db.run {
    order_.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(order_.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_order: Order): Future[Unit] = {
    val orderToUpdate: Order = new_order.copy(id)
    db.run(order_.filter(_.id === id).update(orderToUpdate)).map(_ => ())
  }
}
