package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.sql.Timestamp
import java.time.Instant
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class DiscountRepository @Inject()(val dbConfigProvider: DatabaseConfigProvider, val productRepository: ProductRepository, val userRepository: UserRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class DiscountTable(tag: Tag) extends Table[Discount](tag, "discount") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def product_id = column[Long]("product_id")

    def user_id = column[Long]("user_id")

    def product_fk = foreignKey("prod_fk", product_id, product_)(_.id)

    def user_fk = foreignKey("user_fk", user_id, user_)(_.id)

    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")

    def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")

    def * = (id, product_id, user_id, createdAt, updatedAt) <> ((Discount.apply _).tupled, Discount.unapply)
  }

  import productRepository.ProductTable
  import userRepository.UserTable

  private val product_ = TableQuery[ProductTable]
  private val user_ = TableQuery[UserTable]
  private val discount_ = TableQuery[DiscountTable]

  def create(product_id: Long, user_id: Long, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now())): Future[Discount] = db.run {
    (discount_.map(d => (d.product_id, d.user_id, d.createdAt, d.updatedAt))
      returning discount_.map(_.id)
      into {case ((product_id, user_id, createdAt, updatedAt), id) => Discount(id, product_id, user_id, createdAt, updatedAt)}
      ) += (product_id, user_id, createdAt, updatedAt)
  }

  def list(): Future[Seq[Discount]] = db.run {
    discount_.result
  }

  def getById(id: Long): Future[Discount] = db.run {
    discount_.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Int] = db.run(discount_.filter(_.id === id).delete)

  def update(id: Long, new_discount: Discount): Future[Int] = {
    val discountToUpdate: Discount = new_discount.copy(id)
    db.run(discount_.filter(_.id === id).update(discountToUpdate))
  }
}