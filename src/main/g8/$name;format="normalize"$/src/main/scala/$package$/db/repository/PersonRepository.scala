package $package$.db.repository

import $package$.db.DbContext.ctx
import $package$.db.model.Person
import zio.{Task, ZIO}

trait PersonRepository {
  import ctx._

  def getAllPersons: Task[List[Person]] = ZIO.attempt {
    val q = ctx.quote {
      ctx.query[Person]
    }
    ctx.run(q)
  }
}
