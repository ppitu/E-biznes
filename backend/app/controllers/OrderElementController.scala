package controllers

import akka.actor.ActorSystem
import models.{OrderElement, OrderElementRepository}
import play.api.libs.json.{JsValue, Json}

import javax.inject._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class OrderElementController @Inject()(cc: ControllerComponents, orderElementRepository: OrderElementRepository, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getOrderElements: Action[AnyContent] = Action.async { implicit request =>
    val orderElements = orderElementRepository.list()

    orderElements.map {
      orderElements =>
        Ok(Json.toJson(orderElements))
    }
  }

  def getOrderElement(id: String): Action[AnyContent] = Action.async { implicit request =>
    val orderElement = orderElementRepository.getById(id.toLong)

    orderElement.map {
      orderElement =>
        Ok(Json.toJson(orderElement))
    }
  }

  def updateOrderElement(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[OrderElement].map {
      orderElement =>
        orderElementRepository.update(orderElement.id, orderElement).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update orderElement")))
  }

  def deleteOrderElement(id: String): Action[AnyContent] = Action.async { implicit request =>
    orderElementRepository.delete(id.toLong).map {
      res =>
        Ok(Json.toJson(res))
    }
  }

  def addOrderElement(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[OrderElement].map {
      orderElement =>
        orderElementRepository.create(orderElement.product_id).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json add orderelement")))
  }
}