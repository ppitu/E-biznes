package controllers

import akka.actor.ActorSystem

import javax.inject._
import play.api.mvc._

import scala.concurrent.ExecutionContext

class TransactionController @Inject()(cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getTransactions: Action[AnyContent] = Action {
    Ok("Gets all Transaction")
  }

  def getTransaction(id: Long): Action[AnyContent] = Action {
    Ok("Get Transaction by id")
  }

  def updateTransaction(id: Long): Action[AnyContent] = Action {
    Ok("Update Transaction")
  }

  def deleteTransaction(id: Long): Action[AnyContent] = Action {
    Ok("Delete Transaction")
  }

  def addTransaction(): Action[AnyContent] = Action {
    Ok("Add Transaction")
  }
}