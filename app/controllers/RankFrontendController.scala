package controllers

import javax.inject.Inject
import models.UberResponse
import play.api.libs.json.{Format, Json}
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.Future

class RankFrontendController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def availableUbers: Future[UberResponse] = ???

  case class Lift(driverName: String, price: Float, rating: Int)
  case class AvailableLifts(lifts: Set[Lift])

  private val trialLift1 = Lift("louis", 100000, 1)
  private val trialLift2 = trialLift1.copy(driverName = "callum")
  private val trialLift3 = trialLift1.copy(driverName = "jon")

  private val lifts = AvailableLifts(Set(trialLift1, trialLift2, trialLift3))

  implicit val liftFormats: Format[Lift] = Json.format[Lift]
  implicit val availableLiftsFormats: Format[AvailableLifts] = Json.format[AvailableLifts]

  def availableLifties() = Action { _ =>
    val liftsAsJson = Json.toJson(lifts)

    Ok(liftsAsJson)
  }
}
