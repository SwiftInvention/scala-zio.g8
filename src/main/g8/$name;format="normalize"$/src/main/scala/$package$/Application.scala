package $package$

import $package$.utils.db.Migration

import java.util.logging.Logger
object Application {

  private val log = Logger.getLogger("main")
  def main(args: Array[String]): Unit = {
    println("-" * 50)
    log.info("$name$ starting...")
    Migration.migrate
    println("-" * 50)
  }
}