val sbtScalafmt = "2.4.6"
val sbtScalafix = "0.10.4"

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % sbtScalafmt)
addSbtPlugin("ch.epfl.scala" % "sbt-scalafix" % sbtScalafix)

// Note: an optional plugin for listing outdated dependencies
// Keep commented out to avoid unnecessary dependencies and/or conflicts
// addSbtPlugin("org.jmotor.sbt" % "sbt-dependency-updates" % "1.2.7")