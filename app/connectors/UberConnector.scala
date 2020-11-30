package connectors


import com.uber.sdk.core.auth.OAuth2Credentials
import com.uber.sdk.core.client.{CredentialsSession, SessionConfiguration}
import com.uber.sdk.rides.client.UberRidesApi
import com.uber.sdk.rides.client.services.RidesService
import controllers.PositionRequested
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}

class UberConnector @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def getUbers(positionRequested: PositionRequested) = {
    val clientId = "ENTER CLIENT ID HERE"
    val clientSecret = "ENTER CLIENT SECRET HERE"

    val uberAPI = setUpUberAccess(clientId, "", clientSecret)

      println(uberAPI.getProducts(positionRequested.latitude, positionRequested.longitude).execute())
  }

  private def setUpUberAccess(clientId: String, serverToken: String, clientSecret: String): RidesService = {
    val config: SessionConfiguration = new SessionConfiguration
      .Builder()
      .setClientId(clientId)
      .setClientSecret(clientSecret)
      .setServerToken(serverToken)
      .build()

//     val session: ServerTokenSession = new ServerTokenSession(config)

    val credentials: OAuth2Credentials = new OAuth2Credentials
      .Builder()
      .setSessionConfiguration(config)
      .build()

    val credential = credentials.authenticate("bosh", "1")
    val credentialsSession = new CredentialsSession(config, credential)

      UberRidesApi.`with`(credentialsSession).build().createService()
  }
}