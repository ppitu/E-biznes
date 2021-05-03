package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.sql.Timestamp
import java.time.Instant
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class PromotionRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, productRepository: ProductRepository)(implicit ec: ExecutionContext){
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  private class PromotionTable(tag: Tag) extends Table[Promotion](tag, "promotion") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def product_id = column[Long]("product_id", O.Unique)

    def product_fk = foreignKey("product_fk", product_id, product_)(_.id)

    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")

    def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")

    def * = (id, product_id, createdAt, updatedAt) <> ((Promotion.apply _).tupled, Promotion.unapply)
  }

  import productRepository.ProductTable

  private val product_ = TableQuery[ProductTable]
  private val promotion_ = TableQuery[PromotionTable]

  def create(product_id: Long, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now())): Future[Promotion] = db.run {
    (promotion_.map(p => (p.product_id, p.createdAt, p.updatedAt))
      returning promotion_.map(_.id)
      into {case ((product_id, createdAt, updatedAt), id) => Promotion(id, product_id, createdAt, updatedAt)}
      ) += (product_id, createdAt, updatedAt)
  }

  def list(): Future[Seq[Promotion]] = db.run {
    promotion_.result
  }

  def getById(id: Long): Future[Promotion] = db.run {
    promotion_.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(promotion_.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_promotion: Promotion): Future[Unit] = {
    val promotionToUpdate: Promotion = new_promotion.copy(id)
    db.run(promotion_.filter(_.id === id).update(promotionToUpdate)).map(_ => ())
  }
}
