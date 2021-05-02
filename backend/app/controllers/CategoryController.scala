package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class CategoryController (cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getCategory: Action[AnyContent] = Action {
    Ok("Gets all categories")
  }

  def getCategory(id: Long): Action[AnyContent] = Action {
    Ok("Get category by id")
  }

  def updateCategory(id: Long): Action[AnyContent] = Action {
    Ok("Update category")
  }

  def deleteCategory(id: Long): Action[AnyContent] = Action {
    Ok("Delete category")
  }

  def addCategory(): Action[AnyContent] = Action {
    Ok("Add category")
  }
}