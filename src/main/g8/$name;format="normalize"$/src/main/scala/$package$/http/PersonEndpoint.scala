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

  case class User(name: String) extends AnyVal

  val helloEndpoint: PublicEndpoint[User, Unit, String, Any] = endpoint.get
    .in("hello")
    .in(query[User]("name"))
    .out(stringBody)
  val helloServerEndpoint: ZServerEndpoint[Any, Any] =
    helloEndpoint.serverLogicSuccess(user => ZIO.succeed(s"Hello \${user.name}"))

  val booksListing: PublicEndpoint[Unit, Unit, List[Person], Any] = endpoint.get
    .in("person" / "list" / "all")
    .out(jsonBody[List[Person]])
  val booksListingServerEndpoint: ZServerEndpoint[Any, Any] =
    booksListing.serverLogicSuccess(_ => ZIO.succeed(personList))

  val apiEndpoints: List[ZServerEndpoint[Any, Any]] =
    List(helloServerEndpoint, booksListingServerEndpoint)

  val docEndpoints: List[ZServerEndpoint[Any, Any]] = SwaggerInterpreter()
    .fromServerEndpoints[Task](apiEndpoints, "Swift Invention template", "1.0.0")

  val prometheusMetrics: PrometheusMetrics[Task] = PrometheusMetrics.default[Task]()
  val metricsEndpoint: ZServerEndpoint[Any, Any] = prometheusMetrics.metricsEndpoint

  val all: List[ZServerEndpoint[Any, Any]] = apiEndpoints ++ docEndpoints ++ List(metricsEndpoint)
}