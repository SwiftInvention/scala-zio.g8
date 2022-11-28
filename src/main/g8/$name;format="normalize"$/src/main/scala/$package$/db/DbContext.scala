package $package$.db

import io.getquill.{MysqlZioJdbcContext, SnakeCase}

object DbContext {
  lazy val ctx = new MysqlZioJdbcContext(SnakeCase)

}
