package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.sql.Timestamp
import java.time.Instant
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class OrderElementRepository @Inject()(val dbConfigProvider: DatabaseConfigProvider, val productRepository: ProductRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class OrderElementTable(tag: Tag) extends Table[OrderElement](tag, "order_element") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def product_id = column[Long]("product_id")

    def product_fk = foreignKey("product_fk", product_id, product_)(_.id)

    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")

    def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")

    def * = (id, product_id, createdAt, updatedAt) <> ((OrderElement.apply _).tupled, OrderElement.unapply)
  }

  import productRepository.ProductTable

  private val product_ = TableQuery[ProductTable]
  private val orderElement_ = TableQuery[OrderElementTable]

  def create(product_id: Long, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now())): Future[OrderElement] = db.run {
    (orderElement_.map(o => (o.product_id, o.createdAt, o.updatedAt))
      returning orderElement_.map(_.id)
      into {case ((product_id, createdAt, updatedAt), id) => OrderElement(id, product_id, createdAt, updatedAt)}
      ) += (product_id, createdAt, updatedAt)
  }

  def list(): Future[Seq[OrderElement]] = db.run {
    orderElement_.result
  }

  def getById(id: Long): Future[OrderElement] = db.run {
    orderElement_.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Int] = db.run(orderElement_.filter(_.id === id).delete)

  def update(id: Long, new_order_element: OrderElement): Future[Int] = {
    val orderElementToUpdate: OrderElement = new_order_element.copy(id)
    db.run(orderElement_.filter(_.id === id).update(orderElementToUpdate))
  }
}