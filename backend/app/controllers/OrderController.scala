package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class OrderController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getOrders: Action[AnyContent] = Action {
    Ok("Gets all History")
  }

  def getOrder(id: Long): Action[AnyContent] = Action {
    Ok("Get History by id")
  }

  def updateOrder(id: Long): Action[AnyContent] = Action {
    Ok("Update History")
  }

  def deleteOrder(id: Long): Action[AnyContent] = Action {
    Ok("Delete History")
  }

  def addOrder(): Action[AnyContent] = Action {
    Ok("Add History")
  }
}