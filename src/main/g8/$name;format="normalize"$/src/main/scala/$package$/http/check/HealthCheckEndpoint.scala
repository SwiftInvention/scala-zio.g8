package $package$.http.check

import $package$.db.repository.HealthCheckHelper
import sttp.tapir._
import sttp.tapir.ztapir.ZServerEndpoint
import zio.ZIO

object HealthCheckEndpoint extends HealthCheckHelper {

  val healthChecking: PublicEndpoint[Unit, Unit, Unit, Any] = endpoint
    .name("Healthcheck-endpoint")
    .description("returns 200 if the database is available at the time the request is received")
    .get
    .in("check" / "health")
    .out(emptyOutput)

  val healthCheckingServerEndpoint: ZServerEndpoint[Any, Any] =
    healthChecking.serverLogicSuccess(_ => healthCheck)

  val readinessChecking: PublicEndpoint[Unit, Unit, Unit, Any] = endpoint
    .name("Readiness-endpoint")
    .description("returns 200 if the server is available")
    .get
    .in("check" / "readiness")
    .out(emptyOutput)

  val readinessCheckingServerEndpoint: ZServerEndpoint[Any, Any] =
    readinessChecking.serverLogicSuccess(_ => ZIO.unit)

}
