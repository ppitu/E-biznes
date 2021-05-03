package models

import play.api.libs.json.{Json, OFormat}

import java.sql.Timestamp
import java.time.Instant

case class User(id: Long, name: String, password: String, email: String, createdAt: Timestamp = Timestamp.from(Instant.now()), updatedAt: Timestamp = Timestamp.from(Instant.now()))

object User extends TimeStampMy {
  implicit val userFormat: OFormat[User] = Json.using[Json.WithDefaultValues].format[User]
}
