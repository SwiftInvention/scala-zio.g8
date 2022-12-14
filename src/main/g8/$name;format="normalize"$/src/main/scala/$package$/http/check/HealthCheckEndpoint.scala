package $package$.http.check

import $package$.AppEnv.AppEnv
import $package$.db.repository.HealthCheckHelper
import $package$.http.BaseEndpoint.{makeEndpoint, makeEndpointHandler}
import $package$.http.InternalServerError
import sttp.tapir._
import sttp.tapir.ztapir.ZTapir
import zio.ZIO

object HealthCheckEndpoint extends HealthCheckHelper with ZTapir {

  val databaseCheck: ZServerEndpoint[AppEnv, Any] =
    makeEndpointHandler(
      makeEndpoint(
        "database-health-check",
        "returns 200 if the database is available at the time the request is received"
      ).get
        .in("check" / "database")
        .out(emptyOutput)
    )(_ => databaseHealthCheck.mapError(_ => InternalServerError))

  val serverCheck: ZServerEndpoint[AppEnv, Any] =
    makeEndpointHandler(
      makeEndpoint(
        "server-health-check",
        "returns 200 if the server is available"
      ).get
        .in("check" / "server")
        .out(emptyOutput)
    )(_ => ZIO.unit)
}