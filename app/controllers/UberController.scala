package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}
import views.html.uber

@Singleton
class UberController @Inject()(cc: ControllerComponents, uberPage: uber) extends AbstractController(cc) {

  def get = Action {
    Ok(uberPage)
  }
}
