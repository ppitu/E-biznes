package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class RecomendationController (cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getRecomendation: Action[AnyContent] = Action {
    Ok("Gets all Recomendations")
  }

  def getRecomendation(id: Long): Action[AnyContent] = Action {
    Ok("Get Recomendation by id")
  }

  def updateRecomendation(id: Long): Action[AnyContent] = Action {
    Ok("Update Recomendation")
  }

  def deleteRecomendation(id: Long): Action[AnyContent] = Action {
    Ok("Delete Recomendation")
  }

  def addRecomendation(): Action[AnyContent] = Action {
    Ok("Add Recomendation")
  }
}