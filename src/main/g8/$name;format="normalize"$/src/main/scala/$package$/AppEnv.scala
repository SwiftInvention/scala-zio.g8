package $package$

import $package$.config.HttpServerConfig
import io.getquill.context.ZioJdbc.DataSourceLayer
import javax.sql.DataSource
import zhttp.service.EventLoopGroup
import zhttp.service.server.ServerChannelFactory
import zio._
import zio.clock.Clock

object AppEnv {
  type AppEnv = Has[HttpServerConfig]
    with Has[Clock.Service]
    with Has[DataSource]
    with EventLoopGroup
    with zhttp.service.ServerChannelFactory

  type AppIO[T] = ZIO[AppEnv, Throwable, T]

  def buildLiveEnv: Layer[Serializable, AppEnv] =
    HttpServerConfig.layer ++ Clock.live ++ DataSourceLayer.fromPrefix("mysql") ++ EventLoopGroup
      .auto(0) ++ ServerChannelFactory.auto

}
