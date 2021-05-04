package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.sql.Timestamp
import java.time.Instant
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class CreditCardRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class CreditCardTable(tag: Tag) extends Table[CreditCard](tag, "credit_card") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def holder_name = column[String]("holder_name", O.Unique)

    def number = column[Long]("number")

    def cvv = column[Long]("cvv")

    def date = column[String]("date")

    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")

    def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")

    def * = (id, holder_name, number, cvv, date, createdAt, updatedAt) <> ((CreditCard.apply _).tupled, CreditCard.unapply)
  }


  private val creditCard_ = TableQuery[CreditCardTable]

  def create(holder_name: String, number: Long, cvv: Long, date: String, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now())): Future[CreditCard] = db.run {
    (creditCard_.map(c => (c.holder_name, c.number, c.cvv, c.date, c.createdAt, c.updatedAt))
      returning creditCard_.map(_.id)
      into {case ((holder_name, number, cvv, date, createdAt, updatedAt), id) => CreditCard(id, holder_name, number, cvv, date, createdAt, updatedAt)}
      ) += (holder_name, number, cvv, date, createdAt, updatedAt)
  }

  def list(): Future[Seq[CreditCard]] = db.run {
    creditCard_.result
  }

  def getById(id: Long): Future[CreditCard] = db.run {
    creditCard_.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(creditCard_.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_credit_card: CreditCard): Future[Unit] = {
    val creditCardToUpdate: CreditCard = new_credit_card.copy(id)
    db.run(creditCard_.filter(_.id === id).update(creditCardToUpdate)).map(_ => ())
  }
}
