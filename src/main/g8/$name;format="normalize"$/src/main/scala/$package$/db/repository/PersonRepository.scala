package $package$.db.repository

import $package$.db.DbContext.ctx._
import $package$.db.DbContext._
import $package$.db.model.Person
import $package$.AppEnv.AppIO

trait PersonRepository {
  def getAllPersons: AppIO[List[Person]] = {
    val q = ctx.quote {
      persons
    }
    run(q)
  }
}
