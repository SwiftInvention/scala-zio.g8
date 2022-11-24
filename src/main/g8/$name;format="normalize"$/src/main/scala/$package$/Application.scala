package $package$

import $package$.config.HttpServerConfig
import $package$.http.swagger.SwaggerApiEndpoint.common
import $package$.utils.db.Migration
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import zhttp.http.HttpApp
import zhttp.service.{EventLoopGroup, Server}
import zhttp.service.server.ServerChannelFactory
import zio._

object Application extends App {
  val app: HttpApp[Any, Throwable] = ZioHttpInterpreter().toHttp(common)

  def run(args: List[String]) =
    (for {
      _ <- Migration.migrate
      conf <- ZIO.service[HttpServerConfig]
      _ <- Server(app).withBinding(conf.host, conf.port).start
    } yield ())
      .provideLayer(HttpServerConfig.layer ++ EventLoopGroup.auto(0) ++ ServerChannelFactory.auto)
      .exitCode
}
