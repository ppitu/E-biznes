package controllers

import akka.actor.ActorSystem
import models.{Payment, PaymentRepository}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class PaymentController @Inject()(cc: ControllerComponents, paymentRepository: PaymentRepository, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){
  def getPayments: Action[AnyContent] = Action.async { implicit request =>
    val payments = paymentRepository.list()

    payments.map {
      payments =>
        Ok(Json.toJson(payments))
    }
  }

  def getPayment(id: String): Action[AnyContent] = Action.async { implicit request =>
    val payment = paymentRepository.getById(id.toLong)

    payment.map {
      payment =>
        Ok(Json.toJson(payment))
    }
  }

  def updatePayment(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Payment].map {
      payment =>
        paymentRepository.update(payment.id, payment).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json update payment")))
  }

  def deletePayment(id: String): Action[AnyContent] = Action.async { implicit request =>
    paymentRepository.delete(id.toLong).map {
      res =>
        Ok(Json.toJson(res))
    }
  }

  def addPayment(): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Payment].map {
      payment =>
        paymentRepository.create(payment.user_id, payment.creditCard_id, payment.date).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json add payment")))
  }
}