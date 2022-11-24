package $package$.config

import zio.config.ReadError
import zio.{Has, ZLayer}
import com.typesafe.config.ConfigFactory
import scala.util.Try

final case class HttpServerConfig(host: String, port: Int)

object HttpServerConfig {
  val layer: ZLayer[Any, ReadError[String], Has[HttpServerConfig]] = {
    Try({
      val conf = ConfigFactory.load("application.conf")
      val port = conf.getInt("httpServer.port")
      val host = conf.getString("httpServer.host")
      HttpServerConfig(host, port)
    }).fold(
      e => ZLayer.fail(ReadError.SourceError(e.getMessage)),
      x => ZLayer.succeed(x)
    )
  }
}
