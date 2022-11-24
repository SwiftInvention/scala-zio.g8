import sbt._

object Dependencies {

  lazy val commonDep = Seq(
    "dev.zio" %% "zio" % Versions.zio,
    "dev.zio" %% "zio-config" % Versions.zioConfig,
    "dev.zio" %% "zio-config-typesafe" % Versions.zioConfig,
    "dev.zio" %% "zio-config-magnolia" % Versions.zioConfig
  ) map (_ % Compile)

  lazy val dbDep = Seq(
    "io.getquill" %% "quill-jdbc" % Versions.quill,
    "mysql" % "mysql-connector-java" % Versions.mysql,
    "org.flywaydb" % "flyway-core" % Versions.flyway,
    "org.flywaydb" % "flyway-mysql" % Versions.flyway
  ) map (_ % Compile)

  lazy val httpDep = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-zio1-http-server" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % Versions.tapir
  ) map (_ % Compile)

  lazy val testDep = Seq(
    "dev.zio" %% "zio-test" % Versions.zio,
    "dev.zio" %% "zio-test-sbt" % Versions.zio,
  ) map (_ % Test)
}
