package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

@Singleton
class UserController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getUsers: Action[AnyContent] = Action {
    Ok("Gets all user!!!")
  }

  def getUser(id: Long): Action[AnyContent] = Action {
    Ok("Get user by id")
  }

  def updateUser(id: Long): Action[AnyContent] = Action {
    Ok("Update user")
  }

  def deleteUser(id: Long): Action[AnyContent] = Action {
    Ok("Delete user")
  }

  def addUser(): Action[AnyContent] = Action {
    Ok("Add user")
  }
}
