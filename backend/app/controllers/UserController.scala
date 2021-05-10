package controllers

import akka.actor.ActorSystem
import models.{User, UserRepository}
import play.api.libs.json.{JsValue, Json}

import javax.inject._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(cc: ControllerComponents, userRepository: UserRepository,  actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getUsers: Action[AnyContent] = Action.async { implicit request =>
    val users = userRepository.list()

    users.map {
      users =>
        Ok(Json.toJson(users))
    }
  }

  def getUser(id: String): Action[AnyContent] = Action.async { implicit request =>
    val user = userRepository.getById(id.toLong)

    user.map {
      user =>
        Ok(Json.toJson(user))
    }
  }

  def updateUser(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[User].map {
      user =>
        userRepository.update(user.id, user).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update user")))
  }

  def deleteUser(id: String): Action[AnyContent] = Action.async { implicit request =>
    userRepository.delete(id.toLong).map {
      res =>
        Ok(Json.toJson(res))
    }
  }

  def addUser(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[User].map {
      user =>
        userRepository.create(user.name, user.password, user.email).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json add user")))
  }
}
