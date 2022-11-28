package $package$.http.swagger

import $package$.http.check.HealthCheckEndpoint
import $package$.AppEnv.AppEnv
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.ZTapir

object SwaggerApiEndpoint extends ZTapir {
  val apiEndpoints: List[ZServerEndpoint[AppEnv, Any]] =
    List(
      HealthCheckEndpoint.healthCheckingServerEndpoint,
      HealthCheckEndpoint.readinessCheckingServerEndpoint,
    )

  val docEndpoints: List[ZServerEndpoint[AppEnv, Any]] = SwaggerInterpreter()
    .fromServerEndpoints(apiEndpoints, "project", "0.1.0")

  val common = apiEndpoints ++ docEndpoints
}
