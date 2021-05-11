package controllers

import akka.actor.ActorSystem
import models.{User, UserRepository}
import play.api.libs.json.{JsValue, Json}
import javax.inject._
import play.api.data.Form
import play.api.data.Forms.{longNumber, mapping, nonEmptyText}
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class UserController @Inject()(cc: MessagesControllerComponents, userRepository: UserRepository,  actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends MessagesAbstractController(cc){

  val _userFrom: Form[CreateUserForm] = Form {
    mapping(
      "name" -> nonEmptyText,
      "password" -> nonEmptyText,
      "email" -> nonEmptyText,
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  }

  val _updateUserForm: Form[UpdateUserForm] = Form {
    mapping(
      "id" -> longNumber,
      "name" -> nonEmptyText,
      "password" -> nonEmptyText,
      "email" -> nonEmptyText
    )(UpdateUserForm.apply)(UpdateUserForm.unapply)
  }


  def getUsers: Action[AnyContent] = Action.async { implicit request =>
    val users = userRepository.list()

    users.map {
      users =>
        Ok(Json.toJson(users))
    }
  }

  def getUsersForm: Action[AnyContent] = Action.async { implicit request =>
    val users = userRepository.list()
    users.map { user => Ok(views.html.user.users(user))}
  }

  def getUser(id: String): Action[AnyContent] = Action.async { implicit request =>
    val user = userRepository.getById(id.toLong)

    user.map {
      user =>
        Ok(Json.toJson(user))
    }
  }

  def getUserForm(id: String): Action[AnyContent] = Action.async {
    val user = userRepository.getById(id.toLong)

    user.map {
      case user: User => Ok(views.html.user.user(user))
      case _ => Redirect(routes.UserController.getUsersForm)
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

  def addUserForm(): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    Future(Ok(views.html.user.user_add(_userFrom)))
  }

  def addUserHandler(): Action[AnyContent] = Action.async { implicit request =>
    _userFrom.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest("Error")
        )
      },
      user => {
        userRepository.create(user.name, user.password, user.email).map { _ =>
          Redirect(routes.UserController.getUsersForm).flashing("success" -> "category.created")
        }
      }
    )
  }

  def updateUserForm(id: String): Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    val user = userRepository.getById(id.toLong)

    user.map(user => {
      val useForm = _updateUserForm.fill(UpdateUserForm(user.id, user.name, user.password, user.email))

      Ok(views.html.user.user_update(useForm))
    })
  }

  def updateUserHandler(): Action[AnyContent] = Action.async { implicit request =>
    _updateUserForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(
          BadRequest("Error")
        )
      },
      user => {
        userRepository.update(user.id, User(user.id, user.name, user.password, user.email)).map { _ =>
          Redirect(routes.UserController.getUsersForm).flashing("success" -> "category updates")
        }
      }
    )
  }

}

case class CreateUserForm(name: String, password: String, email: String)
case class UpdateUserForm(id: Long, name: String, password: String, email: String)