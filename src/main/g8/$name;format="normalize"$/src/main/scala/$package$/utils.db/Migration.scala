package $package$.utils.db

import io.getquill.{MysqlJdbcContext, SnakeCase}
import org.flywaydb.core.Flyway

import java.util.logging.Logger
import scala.util.{Failure, Success, Try}

object Migration {

  private val log = Logger.getLogger("migration")
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
        log.warning(s"Migrating the database failed: $e")
        throw RuntimeException("Migrating the database failed")
    }
  }
}
