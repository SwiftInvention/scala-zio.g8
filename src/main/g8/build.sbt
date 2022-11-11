import Dependencies._

ThisBuild / organization := "$organization$"
ThisBuild / scalaVersion := "$scala_version$"
ThisBuild / version := "0.0.1-SNAPSHOT"

lazy val root = (project in file("$name$"))
  .settings(
    settings
      ++ Seq(
      name := "$name$"
    )
  )

lazy val settings = Seq(
  libraryDependencies ++= commonDep ++ testDep ++ tapirCore ++ httpDep ++ dbDep,
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)
