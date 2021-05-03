package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class DiscountController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getDiscounts: Action[AnyContent] = Action {
    Ok("Gets all Discounts")
  }

  def getDiscount(id: Long): Action[AnyContent] = Action {
    Ok("Get Discount by id")
  }

  def updateDiscount(id: Long): Action[AnyContent] = Action {
    Ok("Update Discount")
  }

  def deleteDiscount(id: Long): Action[AnyContent] = Action {
    Ok("Delete Discount")
  }

  def addDiscount(): Action[AnyContent] = Action {
    Ok("Add Discount")
  }
}