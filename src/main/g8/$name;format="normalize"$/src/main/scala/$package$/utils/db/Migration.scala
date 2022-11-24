package $package$.utils.db

import $package$.db.DbContext.ctx.dataSource
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import org.slf4j.{Logger, LoggerFactory}
import zio.ZIO

object Migration {
  val log: Logger = LoggerFactory.getLogger(Migration.getClass.getName)

  lazy val flyway: Flyway = Flyway.configure
    .locations("db/migration")
    .dataSource(dataSource)
    .baselineOnMigrate(true)
    .load

  def migrate: ZIO[Any, Throwable, MigrateResult] =
    for {
      _ <- ZIO.succeed(log.info("Start migrating the database"))
      res <- ZIO.succeed(flyway.migrate())
      _ <- ZIO.succeed(log.info("Migration the database finished successfully"))
    } yield res
}
