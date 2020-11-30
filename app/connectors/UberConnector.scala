package connectors

import com.uber.sdk.core.client.{ServerTokenSession, SessionConfiguration}
import controllers.PositionRequested
import javax.inject.Inject
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, ControllerComponents}

class UberConnector @Inject()(ws: WSClient, cc: ControllerComponents) extends AbstractController(cc) {

  def getUbers(positionRequested: PositionRequested) = {
    val endpoint = s"https://api.uber.com/v1.2/products"
    setConfig("oSaaXZLlw21kHdAct-rqdFLSGIf6t6HZ", "")

    val request = ws.url(endpoint).
      withQueryStringParameters((positionRequested.latitude, positionRequested.longitude)).
      addHttpHeaders(
        "ACCEPT" -> "application/json"
      ).get()

    request
  }

  private def setConfig(clientId: String, serverToken: String) = {
    val config: SessionConfiguration = new SessionConfiguration.Builder()
      .setClientId(clientId)
      .setServerToken(serverToken)
      .build()

     new ServerTokenSession(config)

//    val credentials: OAuth2C = new OAuth2Credentials.Builder()
//      .setSessionConfiguration(config)
//      .build();
    // is this line necessary?
  }
}
 // could base64 the codes