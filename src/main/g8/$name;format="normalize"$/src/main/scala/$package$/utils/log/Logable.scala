package $package$.utils.log

/** This trait is just stub for logging, and it is expected that some kind of logging implementation
 * will be used in the project
 */
trait Logable {
  lazy val log = new Logger()
}

class Logger {
  def info(msg: String): Unit = println("[INFO] " + msg)
  def warn(msg: String): Unit = println("[WARN] " + msg)
}