package $package$.db.repository

import $package$.db.DbContext.ctx._
import $package$.db.DbContext._
import zio.{Task, ZIO}

trait HealthCheckHelper {

  def healthCheck: Task[Unit] = ZIO
    .effect {
      val q = ctx.quote {
        infix"""SELECT 1""".as[Int]
      }
      ctx.run(q)
    }
    .as(())
}
