package $package$

import $package$.config.HttpServerConfig
import $package$.http.swagger.SwaggerApiEndpoint
import $package$.utils.db.Migration
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import zhttp.http.HttpApp
import zhttp.service.Server
import zio._
import zhttp.http._
import AppEnv.{AppEnv, AppIO}
import java.time.Duration

object Application extends App {
  // FIXME: for an unknown reason, the specified duration of 10 seconds simply doesn't work,
  // And the mysql connection timeout is used instead.
  private lazy val composedMiddlewares = Middleware.timeout(Duration.ofSeconds(10))

  private lazy val app: HttpApp[AppEnv, Throwable] =
    ZioHttpInterpreter().toHttp(SwaggerApiEndpoint.common) @@ composedMiddlewares

  def main: AppIO[Unit] =
    (for {
      _    <- Migration.migrate
      conf <- ZIO.service[HttpServerConfig]
      // FIXME: Doesn't compile with the dead code compiler warning turned on
      _ <- Server(app).withBinding(conf.host, conf.port).start
    } yield ())

  def run(args: List[String]) = main
    .provideLayer(AppEnv.buildLiveEnv)
    .exitCode
}