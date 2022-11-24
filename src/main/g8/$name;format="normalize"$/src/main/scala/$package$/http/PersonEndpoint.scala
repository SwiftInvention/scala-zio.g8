package $package$.http

import $package$.db.model.Person
import $package$.db.repository.PersonRepository
import sttp.tapir._
import io.circe.generic.auto._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._

object PersonEndpoint extends PersonRepository {

  val personListing: PublicEndpoint[Unit, Unit, List[Person], Any] =
    endpoint.get
      .name("Default-endpoint")
      .description("Get all persons from database")
      .in("person" / "list" / "all")
      .out(jsonBody[List[Person]])

  val personListingServerLogic = personListing.serverLogicSuccess(_ => getAllPersons)
}
