package $package$.db

import $package$.db.model.Person
import io.getquill.{MysqlJdbcContext, SnakeCase}

object DbContext {

  lazy val ctx = new MysqlJdbcContext(SnakeCase, "mysql")
  val dataSource = ctx.dataSource
  import ctx._

  val persons = quote {
    querySchema[Person]("person")
  }
}
