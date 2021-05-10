package controllers

import akka.actor.ActorSystem
import models.{Address, AddressRepository}
import play.api.Logger
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AddressController @Inject()(cc: ControllerComponents, addressRepository: AddressRepository, actorSystem: ActorSystem)(implicit exec: ExecutionContext) extends AbstractController(cc){

  def getAddresses: Action[AnyContent] = Action.async { request =>
    val addresses = addressRepository.list()

    addresses.map {
      addresses =>
        Ok(Json.toJson(addresses))
    }
  }

  def getAddress(id: String): Action[AnyContent] = Action.async {
    val address = addressRepository.getById(id.toLong)

    address.map {
      address =>
        Ok(Json.toJson(address))
    }
  }

  def updateAddress(id: String): Action[JsValue] = Action.async(parse.json) { implicit request =>
    request.body.validate[Address].map {
      address =>
        addressRepository.update(address.id, address).map {
          res =>
            Ok(Json.toJson(res))
        }
    }.getOrElse(Future.successful(BadRequest("invalid json add address")))
  }

  def deleteAddress(id: String): Action[AnyContent] = Action.async {
    addressRepository.delete(id.toLong).map { res =>
      Ok(Json.toJson(res))
    }
  }

  def addAddress(): Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[Address].map {
      address => {
        addressRepository.create(address.street, address.zipcode, address.number, address.city).map {
          res =>
            Ok(Json.toJson(res))
        }
      }
    }.getOrElse(Future.successful(BadRequest("invalid json add address")))
  }
}