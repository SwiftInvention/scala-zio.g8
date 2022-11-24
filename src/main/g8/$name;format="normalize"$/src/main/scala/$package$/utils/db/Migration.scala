package $package$.utils.db

import $package$.db.DbContext.ctx.dataSource
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.output.MigrateResult
import zio.ZIO

object Migration {

  lazy val flyway: Flyway = Flyway.configure
    .locations("db/migration")
    .dataSource(dataSource)
    .baselineOnMigrate(true)
    .load

  def migrate: ZIO[Any, Throwable, MigrateResult] =
    for {
      _ <- ZIO.effectTotal(println("Start migrating the database"))
      res <- ZIO.effect(flyway.migrate())
      _ <- ZIO.effectTotal(println("Start migrating the database"))
    } yield res
}
