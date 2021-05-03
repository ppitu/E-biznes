package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class PromotionController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getPromotion: Action[AnyContent] = Action {
    Ok("Gets all Promotions")
  }

  def getPromotion(id: Long): Action[AnyContent] = Action {
    Ok("Get Promotion by id")
  }

  def updatePromotion(id: Long): Action[AnyContent] = Action {
    Ok("Update Promotion")
  }

  def deletePromotion(id: Long): Action[AnyContent] = Action {
    Ok("Delete Promotion")
  }

  def addPromotion(): Action[AnyContent] = Action {
    Ok("Add Promotion")
  }
}