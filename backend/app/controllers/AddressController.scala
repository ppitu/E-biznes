package controllers

import akka.actor.ActorSystem
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class AddressController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getAddresses: Action[AnyContent] = Action {
    Ok("Gets all New")
  }

  def getAddress(id: Long): Action[AnyContent] = Action {
    Ok("Get New by id")
  }

  def updateAddress(id: Long): Action[AnyContent] = Action {
    Ok("Update New")
  }

  def deleteAddress(id: Long): Action[AnyContent] = Action {
    Ok("Delete New")
  }

  def addAddress(): Action[AnyContent] = Action {
    Ok("Add New")
  }
}