package controllers

import javax.inject.Inject
import models.UberResponse
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.Future

class RankFrontendController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def availableUbers: Future[UberResponse] = ???
}
