package $package$.http.check

import $package$.db.repository.HealthCheckHelper
import sttp.tapir._
import sttp.tapir.ztapir.ZServerEndpoint
import zio.ZIO

object HealthCheckEndpoint extends HealthCheckHelper {

  val healthChecking: Endpoint[Unit, Unit, String, Int, Any] = endpoint
    .name("Healthcheck-endpoint")
    .description("returns 200 if the database is available at the time the request is received")
    .get
    .in("check" / "health")
    .errorOut(stringBody)
    .out(plainBody[Int])

  val healthCheckingServerEndpoint: ZServerEndpoint[Any, Any] =
    healthChecking.serverLogicSuccess(_ => healthCheck)

  val readinessChecking: Endpoint[Unit, Unit, Unit, Int, Any] = endpoint
    .name("Readiness-endpoint")
    .description("returns 200 if the server is available")
    .get
    .in("check" / "readiness")
    .out(plainBody[Int])

  val readinessCheckingServerEndpoint: ZServerEndpoint[Any, Any] =
    readinessChecking.serverLogicSuccess(_ => ZIO.effect(200))
}
