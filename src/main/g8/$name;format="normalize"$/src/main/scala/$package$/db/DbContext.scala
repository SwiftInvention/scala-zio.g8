package $package$.db

import io.getquill.{MysqlJdbcContext, SnakeCase}

case object DbContext {
  lazy val ctx = new MysqlJdbcContext(SnakeCase, "ctx")
}
