package $package$

import $package$.config.HttpServerConfig
import $package$.http.swagger.SwaggerApiEndpoint
import $package$.utils.db.Migration
import sttp.tapir.server.ziohttp.ZioHttpInterpreter
import zhttp.http.HttpApp
import zhttp.service.Server
import zio._
import zhttp.http._
import AppEnv.AppEnv
import java.time.Duration

object Application extends App {
  // FIXME: for an unknown reason, the specified duration of 10 seconds simply doesn't work,
  // And the mysql connection timeout is used instead.
  private val composedMiddlewares = Middleware.timeout(Duration.ofSeconds(10))

  private val app: HttpApp[AppEnv, Throwable] =
    ZioHttpInterpreter().toHttp(SwaggerApiEndpoint.common) @@ composedMiddlewares

  private val main: ZIO[AppEnv, Throwable, Nothing] = {
    val server: ZManaged[AppEnv, Throwable, Server.Start] =
      for {
        conf <- ZIO.service[HttpServerConfig].toManaged_
        serverStart <- Server(app).withBinding(conf.host, conf.port).make
      } yield serverStart

    Migration.migrate *> server.use(start =>
      console.putStrLn(s"Server started on port \${start.port}")
        *> ZIO.never
    )
  }

  def run(args: List[String]): URIO[zio.ZEnv, ExitCode] =
    main.provideLayer(AppEnv.buildLiveEnv).exitCode
}