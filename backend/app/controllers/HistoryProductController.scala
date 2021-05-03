package controllers

import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class HistoryProductController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getHistoryProduct: Action[AnyContent] = Action {
    Ok("Gets all HistoryProducts")
  }

  def getHistoryProduct(id: Long): Action[AnyContent] = Action {
    Ok("Get HistoryProduct by id")
  }

  def updateHistoryProduct(id: Long): Action[AnyContent] = Action {
    Ok("Update HistoryProduct")
  }

  def deleteHistoryProduct(id: Long): Action[AnyContent] = Action {
    Ok("Delete HistoryProduct")
  }

  def addHistoryProduct(): Action[AnyContent] = Action {
    Ok("Add HistoryProduct")
  }
}