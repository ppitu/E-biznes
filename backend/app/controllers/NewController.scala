package controllers

import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class NewController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getNews: Action[AnyContent] = Action {
    Ok("Gets all New")
  }

  def getNew(id: Long): Action[AnyContent] = Action {
    Ok("Get New by id")
  }

  def updateNew(id: Long): Action[AnyContent] = Action {
    Ok("Update New")
  }

  def deleteNew(id: Long): Action[AnyContent] = Action {
    Ok("Delete New")
  }

  def addNew(): Action[AnyContent] = Action {
    Ok("Add New")
  }
}