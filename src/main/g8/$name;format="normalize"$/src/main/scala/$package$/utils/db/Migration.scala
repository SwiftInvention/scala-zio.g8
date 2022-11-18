package $package$.utils.db

import $package$.db.DbContext.ctx
import $package$.utils.log.Logable
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import zio.ZIO

object Migration extends Logable {

  lazy val flyway: Flyway = Flyway.configure
    .locations("db/migration")
    .dataSource(ctx.dataSource)
    .baselineOnMigrate(true)
    .load

  def migrate: ZIO[Any, Throwable, MigrateResult] = for {
    _ <- log.info("Start migrating the database")
    res <- ZIO.attempt(flyway.migrate())
    _ <- log.info("Migration the database finished successfully")
  } yield res
}
