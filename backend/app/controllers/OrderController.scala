package controllers

import akka.actor.ActorSystem
import models.{Order, OrderRepository}
import play.api.libs.json.{JsValue, Json}

import javax.inject._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class OrderController @Inject()(cc: ControllerComponents, orderRepository: OrderRepository, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getOrders: Action[AnyContent] = Action.async { implicit request =>
    val orders = orderRepository.list()

    orders.map {
      orders =>
        Ok(Json.toJson(orders))
    }
  }

  def getOrder(id: String): Action[AnyContent] = Action.async { implicit request =>
    val order = orderRepository.getById(id.toLong)

    order.map {
      order =>
        Ok(Json.toJson(order))
    }
  }

  def updateOrder(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Order].map {
      order =>
        orderRepository.update(order.id, order).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update order")))
  }

  def deleteOrder(id: String): Action[AnyContent] = Action.async { implicit request =>
    orderRepository.delete(id.toLong).map {
      res =>
        Ok(Json.toJson(res))
    }

  }

  def addOrder(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Order].map {
      order =>
        orderRepository.create(order.user_id, order.amount, order.date).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json add order")))
  }
}