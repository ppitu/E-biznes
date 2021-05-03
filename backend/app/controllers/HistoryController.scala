package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class HistoryController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getHistory: Action[AnyContent] = Action {
    Ok("Gets all History")
  }

  def getHistory(id: Long): Action[AnyContent] = Action {
    Ok("Get History by id")
  }

  def updateHistory(id: Long): Action[AnyContent] = Action {
    Ok("Update History")
  }

  def deleteHistory(id: Long): Action[AnyContent] = Action {
    Ok("Delete History")
  }

  def addHistory(): Action[AnyContent] = Action {
    Ok("Add History")
  }
}