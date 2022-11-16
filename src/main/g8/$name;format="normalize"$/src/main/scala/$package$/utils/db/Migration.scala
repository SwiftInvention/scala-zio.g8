package $package$.utils.db

import $package$.utils.log.Logable
import io.getquill.{MysqlJdbcContext, SnakeCase}
import org.flywaydb.core.Flyway
import scala.util.{Failure, Success, Try}

object Migration extends Logable {

  val ctx = new MysqlJdbcContext(SnakeCase, "ctx")

  private val flyway = Flyway.configure
    .locations("db/migration")
    .dataSource(ctx.dataSource)
    .baselineOnMigrate(true)
    .load

  def migrate = {
    log.info("Start migrating the database")
    val tryMigrate = Try {
      flyway.migrate()
    }

    tryMigrate match {
      case Success(_) => log.info("Migrating the database finished successful")
      case Failure(e) =>
        log.warn("Migrating the database failed: " + e)
        throw new RuntimeException("Migrating the database failed")
    }
  }
}
