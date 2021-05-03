package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class SaleController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getSales: Action[AnyContent] = Action {
    Ok("Gets all Sales")
  }

  def getSale(id: Long): Action[AnyContent] = Action {
    Ok("Get Sale by id")
  }

  def updateSale(id: Long): Action[AnyContent] = Action {
    Ok("Update Sale")
  }

  def deleteSale(id: Long): Action[AnyContent] = Action {
    Ok("Delete Sale")
  }

  def addSale(): Action[AnyContent] = Action {
    Ok("Add Sale")
  }
}