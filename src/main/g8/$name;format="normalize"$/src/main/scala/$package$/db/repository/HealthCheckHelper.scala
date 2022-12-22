package $package$.db.repository

import $package$.db.DbContext.ctx._
import $package$.db.DbContext._
import $package$.AppEnv.AppIO

trait HealthCheckHelper {

  def databaseHealthCheck: AppIO[Unit] = {
    val q = ctx.quote {
      infix"""SELECT 1""".as[Int]
    }
    run(q).unit
  }
}
