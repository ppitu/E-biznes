package models

import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile

import java.sql.Timestamp
import java.time.Instant
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserRepository @Inject()(dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext){
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  import dbConfig._
  import profile.api._

  class UserTable(tag: Tag) extends Table[User](tag, "user") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name", O.Unique)

    def password = column[String]("password")

    def email = column[String]("email")

    def createdAt: Rep[Timestamp] = column[Timestamp]("created_at")

    def updatedAt: Rep[Timestamp] = column[Timestamp]("updated_at")

    def * = (id, name, password, email, createdAt, updatedAt) <> ((User.apply _).tupled, User.unapply)
  }

  private val user_ = TableQuery[UserTable]

  def create(name: String, password: String, email: String, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now())): Future[User] = db.run {
    (user_.map(u => (u.name, u.password, u.email, u.createdAt, u.updatedAt))
      returning user_.map(_.id)
      into {case ((name, password, email, createdAt, updatedAt), id) => User(id, name, password, email, createdAt, updatedAt)}
      ) += (name, password, email, createdAt, updatedAt)
  }

  def list(): Future[Seq[User]] = db.run {
    user_.result
  }

  def getById(id: Long): Future[User] = db.run {
    user_.filter(_.id === id).result.head
  }

  def delete(id: Long): Future[Unit] = db.run(user_.filter(_.id === id).delete).map(_ => ())

  def update(id: Long, new_product: User): Future[Unit] = {
    val userToUpdate: User = new_product.copy(id)
    db.run(user_.filter(_.id === id).update(userToUpdate)).map(_ => ())
  }
}
