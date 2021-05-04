package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.sql.Timestamp
import java.time.Instant
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}


@Singleton
class PaymentRepository @Inject()(dbConfigProvider: DatabaseConfigProvider, val userRepository: UserRepository, val creditCardRepository: CreditCardRepository)(implicit ec: ExecutionContext) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class PaymentTable(tag: Tag) extends Table[Payment](tag, "payment") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def user_id = column[Long]("user_id")

    def creditCard_id = column[Long]("credit_card_id")

    def date = column[String]("date")

    def user_fk = foreignKey("user_fk", user_id, user_)(_.id)

    def creditCard_fk = foreignKey("cred_fk", creditCard_id, creditCard_)(_.id)

    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")

    def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")

    def * = (id, user_id, creditCard_id, date, createdAt, updatedAt) <> ((Payment.apply _).tupled, Payment.unapply)
  }

  import userRepository.UserTable
  import creditCardRepository.CreditCardTable

  private val user_ = TableQuery[UserTable]
  private val creditCard_ = TableQuery[CreditCardTable]
  private val payment_ = TableQuery[PaymentTable]

  def create(user_id: Long, creditCard_id: Long, date: String, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now())): Future[Payment] = db.run {
    (payment_.map(p => (p.user_id, p.creditCard_id, p.date, p.createdAt, p.updatedAt))
      returning payment_.map(_.id)
      into {case ((user_id, creditCard_id, date, createdAt, updatedAt), id) => Payment(id, user_id, creditCard_id, date, createdAt, updatedAt)}
      ) += (user_id, creditCard_id, date, createdAt, updatedAt)
  }

  def list(): Future[Seq[Payment]] = db.run {
    payment_.result
  }

  def getById(id: Long): Future[Payment] = db.run {
    payment_.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(payment_.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_payment: Payment): Future[Unit] = {
    val paymentToUpdate: Payment = new_payment.copy(id)
    db.run(payment_.filter(_.id === id).update(paymentToUpdate)).map(_ => ())
  }
}
