package connectors

import com.uber.sdk.core.client.{ServerTokenSession, SessionConfiguration}
import javax.inject.Inject
import play.api.libs.ws.WSClient
import play.api.mvc.{AbstractController, ControllerComponents}

class UberConnector @Inject()(ws: WSClient, cc: ControllerComponents) extends AbstractController(cc) {

  def getUbers(latitude: Float, longitude: Float) = {
    val endpoint = s"https://api.uber.com/v1.2/products"
    setConfig("ENTER CLIENT ID HERE", "")

    val request = ws.url(endpoint).
      withQueryStringParameters((latitude.toString, longitude.toString)).
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

     new ServerTokenSession(config) // is this line necessary?
  }
}
 // could base64 the codes