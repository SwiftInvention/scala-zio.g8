package $package$.db

import $package$.db.model.Person
import io.getquill.{MysqlJdbcContext, SnakeCase}

case object DbContext {
  lazy val ctx = new MysqlJdbcContext(SnakeCase, "ctx")

  import ctx._
  val persons = quote {
    querySchema[Person]("person", _.name -> "name", _.birthDate -> "birth_date")
  }
}
