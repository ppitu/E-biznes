package controllers

import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.ExecutionContext

class ProductController (cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getProducts: Action[AnyContent] = Action {
    Ok("Gets all products")
  }

  def getProduct(id: Long): Action[AnyContent] = Action {
    Ok("Get product by id")
  }

  def updateProduct(id: Long): Action[AnyContent] = Action {
    Ok("Update product")
  }

  def deleteProduct(id: Long): Action[AnyContent] = Action {
    Ok("Delete product")
  }

  def addProduct(): Action[AnyContent] = Action {
    Ok("Add product")
  }
}
