package $package$.db.repository

import $package$.db.DbContext.ctx
import $package$.db.model.Person
import zio.{Task, ZIO}

trait PersonRepository {
  import ctx._

  val persons = quote {
    querySchema[Person]("person", _.name -> "name", _.birthDate -> "birth_date")
  }

  def getAllPersons: Task[List[Person]] = ZIO.attempt {
    val q = ctx.quote {
      persons
    }
    ctx.run(q)
  }
}
