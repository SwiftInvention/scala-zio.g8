package $package$.utils.db

import $package$.db.DbContext.ctx
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import org.slf4j.{Logger, LoggerFactory}
import zio.ZIO

object Migration {

  val log: Logger = LoggerFactory.getLogger(Migration.getClass.getName)

  lazy val flyway: Flyway = Flyway.configure
    .locations("db/migration")
    .dataSource(ctx.dataSource)
    .baselineOnMigrate(true)
    .load

  def migrate: ZIO[Any, Throwable, MigrateResult] = for {
    _ <- ZIO.attempt(log.info("Start migrating the database"))
    res <- ZIO.attempt(flyway.migrate())
    _ <- ZIO.attempt(log.info("Migration the database finished successfully"))
  } yield res
}
