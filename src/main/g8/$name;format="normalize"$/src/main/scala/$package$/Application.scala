package $package$

import $package$.utils.db.Migration.ctx._
import $package$.utils.db.Migration
import $package$.utils.log.Logable
import $package$.db.model.Person
object Application extends Logable {

  def main(args: Array[String]): Unit = {
    println("-" * 50)
    log.info("$name$ starting...")
    Migration.migrate
    println("-" * 50)

    val q = quote {
      query[Person].filter(_.name equals "Martin Odersky")
    }
    val result = run(q)
    println(result)
  }
}