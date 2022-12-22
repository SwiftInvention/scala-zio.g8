package $package$.http.swagger

import $package$.AppEnv.AppEnv
import $package$.http.check.HealthCheckEndpoint
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.ZTapir

object SwaggerApiEndpoint extends ZTapir {
  private val healthCheckEndpoints = List(
    HealthCheckEndpoint.serverCheck,
    HealthCheckEndpoint.databaseCheck
  )

  private val apiEndpoints = healthCheckEndpoints

  private val docEndpoints: List[ZServerEndpoint[AppEnv, Any]] = SwaggerInterpreter()
    .fromServerEndpoints(apiEndpoints, "project", "0.1.0")

  val common: List[SwaggerApiEndpoint.ZServerEndpoint[AppEnv, Any]] = apiEndpoints ++ docEndpoints
}
