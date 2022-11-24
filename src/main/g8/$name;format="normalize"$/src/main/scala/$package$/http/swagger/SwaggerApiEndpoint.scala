package $package$.http.swagger

import $package$.http.PersonEndpoint.personListingServerLogic
import $package$.http.check.HealthCheckEndpoint.{healthCheckingServerEndpoint, readinessCheckingServerEndpoint}
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.ZServerEndpoint
import zio.Task

object SwaggerApiEndpoint {

  val apiEndpoints: List[ZServerEndpoint[Any, Any]] =
    List(
      healthCheckingServerEndpoint,
      readinessCheckingServerEndpoint,
      personListingServerLogic,
    )

  val docEndpoints: List[ZServerEndpoint[Any, Any]] = SwaggerInterpreter()
    .fromServerEndpoints[Task](apiEndpoints, "project", "0.1.0")


  val common = apiEndpoints ++ docEndpoints
}