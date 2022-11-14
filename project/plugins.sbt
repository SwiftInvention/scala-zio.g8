addSbtPlugin("org.foundweekends.giter8" %% "sbt-giter8" % "0.16.0")
libraryDependencies += { "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value }

// Note: an optional plugin for listing outdated dependencies
// addSbtPlugin("org.jmotor.sbt" % "sbt-dependency-updates" % "1.2.7")