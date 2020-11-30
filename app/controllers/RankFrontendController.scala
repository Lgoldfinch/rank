package controllers

import connectors.UberConnector
import javax.inject.Inject
import models.UberResponse
import play.api.libs.json.{Format, Json}
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

class RankFrontendController @Inject()(uberConnector: UberConnector, cc: ControllerComponents)(implicit ec: ExecutionContext) extends AbstractController(cc) {

  def availableUbers: Future[UberResponse] = ???

  case class Lift(driverName: String, price: Float, rating: Int)
  case class AvailableLifts(lifts: Set[Lift])

  private val trialLift1 = Lift("louis", 100000, 1)
  private val trialLift2 = trialLift1.copy(driverName = "callum")
  private val trialLift3 = trialLift1.copy(driverName = "jon")

  private val lifts = AvailableLifts(Set(trialLift1, trialLift2, trialLift3))

  implicit val liftFormats: Format[Lift] = Json.format[Lift]
  implicit val availableLiftsFormats: Format[AvailableLifts] = Json.format[AvailableLifts]

  val latitude = 37.7752315.toFloat
  val longitude = 122.418075.toFloat

  val position = PositionRequested(latitude, longitude)

  def availableLifties() = Action { _ =>
    val liftsAsJson = Json.toJson(lifts)

    val availableUbers = uberConnector.getUbers(position)

    Ok(liftsAsJson)
  }
}

case class PositionRequested(latitude: Float, longitude: Float)

