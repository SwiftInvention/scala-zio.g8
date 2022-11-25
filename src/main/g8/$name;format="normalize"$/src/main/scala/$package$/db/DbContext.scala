package $package$.db

import $package$.db.model.Person
import io.getquill.{MysqlZioJdbcContext, SnakeCase}

object DbContext {
  lazy val ctx = new MysqlZioJdbcContext(SnakeCase)

  val persons = ctx.quote {
    ctx.querySchema[Person]("person")
  }
}
