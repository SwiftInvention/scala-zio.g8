import sbt._

object Dependencies {

  lazy val commonDep = Seq(
    "dev.zio" %% "zio" % Versions.zio,
    "dev.zio" %% "zio-macros" % Versions.zio,
    "com.beachape" %% "enumeratum-quill" % Versions.enumeratum,
    "com.softwaremill.sttp.tapir" %% "tapir-enumeratum" % Versions.tapir,
    "io.circe" %% "circe-generic" % Versions.circe,
    "io.circe" %% "circe-generic-extras" % Versions.circe,
    "io.lemonlabs" %% "scala-uri" % Versions.scalaUri,
    "io.scalaland" %% "chimney" % Versions.chimney,
    "org.mnode.ical4j" % "ical4j" % Versions.ical,
    "io.github.kitlangton" %% "zio-magic" % Versions.zioMagic
  ) map (_ % Compile)

  lazy val tapirCore = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core" % Versions.tapir
  )

  lazy val dbDep = Seq(
    "io.getquill" %% "quill-jdbc" % Versions.quill,
    "mysql" % "mysql-connector-java" % Versions.mysql,
    "org.mindrot" % "jbcrypt" % "0.4"
  ) map (_ % Compile)

  lazy val httpDep = Seq(
    "com.softwaremill.sttp.tapir" %% "tapir-core" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-enumeratum" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % Versions.tapir,
    "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui" % Versions.tapir,
    "io.d11" %% "zhttp" % Versions.zioHttpVersion,
//    "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp,
//    "com.typesafe.akka" %% "akka-http2-support" % Versions.akkaHttp,
//    "com.typesafe.akka" %% "akka-stream" % Versions.akka,
//    "com.typesafe.akka" %% "akka-actor" % Versions.akka,
//    "com.typesafe.akka" %% "akka-discovery" % Versions.akka
  ) map (_ % Compile)

  lazy val testDep = Seq(
    "dev.zio" %% "zio-test" % Versions.zio,
    "dev.zio" %% "zio-test-sbt" % Versions.zio,
    "com.dimafeng" %% "testcontainers-scala" % Versions.testcontainersScala,
    "com.dimafeng" %% "testcontainers-scala-mysql" % Versions.testcontainersScala,
    "org.flywaydb" % "flyway-mysql" % Versions.flyway
  ) map (_ % Test)
}
