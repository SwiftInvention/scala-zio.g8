package $package$.http

import $package$.db.model._

import sttp.tapir._
import io.circe.generic.auto._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import sttp.tapir.server.metrics.prometheus.PrometheusMetrics
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.ztapir.ZServerEndpoint
import zio.{Task, ZIO}

import java.time.Instant

object PersonEndpoint {

  val personList: List[Person] = List(
    Person("Person1", Instant.now),
    Person("Person2", Instant.now),
    Person("Person3", Instant.now),
    Person("Person4", Instant.now),
    Person("Person5", Instant.now)
  )

  val personListing: PublicEndpoint[Unit, Unit, List[Person], Any] = endpoint
    .name("Default-endpoint")
    .description("Get all persons from database")
    .get
    .in("person" / "list" / "all")
    .out(jsonBody[List[Person]])

  val personListingServerEndpoint: ZServerEndpoint[Any, Any] =
    personListing.serverLogicSuccess(_ => ZIO.succeed(personList))

  val apiEndpoints: List[ZServerEndpoint[Any, Any]] =
    List(personListingServerEndpoint)

  val docEndpoints: List[ZServerEndpoint[Any, Any]] = SwaggerInterpreter()
    .fromServerEndpoints[Task](apiEndpoints, "$name$", "1.0.0")

  val prometheusMetrics: PrometheusMetrics[Task] = PrometheusMetrics.default[Task]()
  val metricsEndpoint: ZServerEndpoint[Any, Any] = prometheusMetrics.metricsEndpoint

  val all: List[ZServerEndpoint[Any, Any]] = apiEndpoints ++ docEndpoints ++ List(metricsEndpoint)
}