package $package$.db.repository

import $package$.db.DbContext.ctx._
import $package$.db.DbContext._
import $package$.db.model.Person
import zio.{Task, ZIO}

trait PersonRepository {
  def getAllPersons: Task[List[Person]] = ZIO.succeed {
    val q = ctx.quote {
      persons
    }
    ctx.run(q)
  }
}
