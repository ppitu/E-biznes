package controllers

import akka.actor.ActorSystem
import models.{Discount, DiscountRepository}
import play.api.libs.json.{JsValue, Json}

import javax.inject._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class DiscountController @Inject()(cc: ControllerComponents, discountRepository: DiscountRepository,  actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getDiscounts: Action[AnyContent] = Action.async { implicit request =>
    val discounts = discountRepository.list()

    discounts.map {
      discounts =>
        Ok(Json.toJson(discounts))
    }
  }

  def getDiscount(id: String): Action[AnyContent] = Action.async { implicit request =>
    val discount = discountRepository.getById(id.toLong)

    discount.map {
      discount =>
        Ok(Json.toJson(discount))
    }
  }

  def updateDiscount(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Discount].map {
      discount =>
        discountRepository.update(discount.id, discount).map { res =>
          Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update discount")))
  }

  def deleteDiscount(id: String): Action[AnyContent] = Action.async { implicit request =>
    discountRepository.delete(id.toLong).map {
      res =>
        Ok(Json.toJson(res))
    }
  }

  def addDiscount(): Action[JsValue] = Action.async(parse.json) { implicit  request =>
    request.body.validate[Discount].map {
      discount =>
        discountRepository.create(discount.product_id, discount.user_id).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json add discount")))
  }
}