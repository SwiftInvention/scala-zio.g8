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
  scalacOptions ++= Seq(
    "-deprecation",           // Emit warning and location for usages of deprecated APIs.
    "-encoding", "utf-8",     // Specify character encoding used by source files.
    "-explaintypes",          // Explain type errors in more detail.
    "-feature",               // Emit warning and location for usages of features that should be imported explicitly.
    "-language:higherKinds",  // Allow higher-kinded types
    "-language:implicitConversions",  // Allow definition of implicit functions called views
    "-unchecked",                     // Enable additional warnings where generated code depends on assumptions.
    "-Xlint:infer-any",               // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator",    // A string literal appears to be missing an interpolator id.
    "-Ywarn-unused:implicits",        // Warn if an implicit parameter is unused.
    "-Ywarn-unused:imports",          // Warn if an import selector is not referenced.
    "-Ywarn-unused:locals",           // Warn if a local definition is unused.
    "-Ywarn-unused:params",           // Warn if a value parameter is unused.
    "-Ywarn-unused:patvars",          // Warn if a variable bound in a pattern is unused.
    "-Ywarn-unused:privates",         // Warn if a private member is unused.
    "-Ywarn-value-discard",           // Warn when non-Unit expression results are unused.
    "-Werror"                         // Fail the compilation if there are any warnings.
  ),
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)
