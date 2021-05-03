package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class RecommendationController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getRecommendations: Action[AnyContent] = Action {
    Ok("Gets all Recommendations")
  }

  def getRecommendation(id: Long): Action[AnyContent] = Action {
    Ok("Get Recomendation by id")
  }

  def updateRecommendation(id: Long): Action[AnyContent] = Action {
    Ok("Update Recomendation")
  }

  def deleteRecommendation(id: Long): Action[AnyContent] = Action {
    Ok("Delete Recomendation")
  }

  def addRecommendation(): Action[AnyContent] = Action {
    Ok("Add Recomendation")
  }
}