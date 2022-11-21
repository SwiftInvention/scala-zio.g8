package $package$

import $package$.config.HttpServerConfig
import $package$.http._
import $package$.db.repository.PersonRepository
import $package$.utils.db.Migration
import org.slf4j.LoggerFactory
import sttp.tapir.server.interceptor.log.DefaultServerLog
import sttp.tapir.server.ziohttp.{ZioHttpInterpreter, ZioHttpServerOptions}
import zhttp.http.HttpApp
import zhttp.service.server.ServerChannelFactory
import zhttp.service.{EventLoopGroup, Server}
import zio.{Console, Scope, Task, ZIO, ZIOAppArgs, ZIOAppDefault}

object Application extends ZIOAppDefault with PersonRepository {

  val log = LoggerFactory.getLogger(Application.getClass.getName)

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] = {
    val serverOptions: ZioHttpServerOptions[Any] =
      ZioHttpServerOptions.customiseInterceptors
        .serverLog(
          DefaultServerLog[Task](
            doLogWhenReceived = msg => ZIO.succeed(log.debug(msg)),
            doLogWhenHandled =
              (msg, error) => ZIO.succeed(error.fold(log.debug(msg))(err => log.debug(msg, err))),
            doLogAllDecodeFailures =
              (msg, error) => ZIO.succeed(error.fold(log.debug(msg))(err => log.debug(msg, err))),
            doLogExceptions = (msg: String, ex: Throwable) => ZIO.succeed(log.debug(msg, ex)),
            noLog = ZIO.unit
          )
        )
        .metricsInterceptor(PersonEndpoint.prometheusMetrics.metricsInterceptor())
        .options

    val app: HttpApp[Any, Throwable] =
      ZioHttpInterpreter(serverOptions).toHttp(PersonEndpoint.all)

    (for {
      _ <- Migration.migrate
      conf <- ZIO.service[HttpServerConfig]
      port = conf.port
      serverStart <- Server(app).withPort(port).make
      _ <- Console.printLine(
        s"Go to http://localhost:\${port}/docs to open SwaggerUI. Press ENTER key to exit."
      )
      _ <- Console.readLine
    } yield serverStart)
      .provideSomeLayer(HttpServerConfig.layer ++ EventLoopGroup.auto(0) ++ ServerChannelFactory.auto ++ Scope.default)
      .exitCode
  }
}