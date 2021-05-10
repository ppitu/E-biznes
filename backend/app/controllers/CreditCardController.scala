package controllers

import akka.actor.ActorSystem
import models.{CreditCard, CreditCardRepository}
import play.api.libs.json.{JsValue, Json}

import javax.inject._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

class CreditCardController @Inject()(cc: ControllerComponents, creditCardRepository: CreditCardRepository, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getCreditCards: Action[AnyContent] = Action.async { implicit request =>
    val creditCards = creditCardRepository.list()

    creditCards.map {
      creditCards =>
        Ok(Json.toJson(creditCards))
    }
  }

  def getCreditCard(id: String): Action[AnyContent] = Action.async { implicit request =>
    val creditCard = creditCardRepository.getById(id.toLong)

    creditCard.map {
      creditCard =>
        Ok(Json.toJson(creditCard))
    }
  }

  def updateRCreditCard(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[CreditCard].map {
      creditCard =>
        creditCardRepository.update(creditCard.id, creditCard).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update category")))
  }

  def deleteCreditCard(id: String): Action[AnyContent] = Action.async {
    creditCardRepository.delete(id.toLong).map {
      res =>
        Ok(Json.toJson(res))
    }
  }

  def addCreditCard(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[CreditCard].map {
      creditCard =>
        creditCardRepository.create(creditCard.holder_name, creditCard.number, creditCard.cvv, creditCard.date).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update category")))
  }
}