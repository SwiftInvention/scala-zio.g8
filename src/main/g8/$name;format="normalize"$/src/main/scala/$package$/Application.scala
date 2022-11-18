package $package$

import $package$.db.repository.PersonRepository
import $package$.utils.db.Migration
import $package$.utils.log.Logable
import zio._

object Application extends Logable with ZIOAppDefault with PersonRepository {
  def run =
    for {
      _      <- Migration.migrate
      result <- getAllPersons
      _      <- log.info(result.toString())
    } yield ()
}