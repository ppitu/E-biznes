package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class OrderElementController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getOrderElements: Action[AnyContent] = Action {
    Ok("Gets all Sales")
  }

  def getOrderElement(id: Long): Action[AnyContent] = Action {
    Ok("Get Sale by id")
  }

  def updateOrderElement(id: Long): Action[AnyContent] = Action {
    Ok("Update Sale")
  }

  def deleteOrderElement(id: Long): Action[AnyContent] = Action {
    Ok("Delete Sale")
  }

  def addOrderElement(): Action[AnyContent] = Action {
    Ok("Add Sale")
  }
}