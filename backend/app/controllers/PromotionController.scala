package controllers

import akka.actor.ActorSystem
import models.{Promotion, PromotionRepository}
import play.api.libs.json.{JsValue, Json}

import javax.inject._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class PromotionController @Inject()(cc: ControllerComponents, promotionRepository: PromotionRepository, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getPromotions: Action[AnyContent] = Action.async { implicit request =>
    val promotions = promotionRepository.list()

    promotions.map {
      promotions =>
        Ok(Json.toJson(promotions))
    }
  }

  def getPromotion(id: String): Action[AnyContent] = Action.async { implicit request =>
    val promotion = promotionRepository.getById(id.toLong)

    promotion.map {
      promotion =>
        Ok(Json.toJson(promotion))
    }
  }

  def updatePromotion(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Promotion].map {
      promotion =>
        promotionRepository.update(promotion.id, promotion).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update promotion")))
  }

  def deletePromotion(id: String): Action[AnyContent] = Action.async { implicit request =>
    promotionRepository.delete(id.toLong).map { res =>
      Ok(Json.toJson(res))
    }
  }

  def addPromotion(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Promotion].map {
      promotion =>
        promotionRepository.create(promotion.product_id).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json add promotion")))
  }
}