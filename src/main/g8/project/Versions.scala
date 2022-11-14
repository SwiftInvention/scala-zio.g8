object Versions {
  val akka          = "2.6.19"
  val akkaHttp      = "10.2.9"
  val akkaHttpCirce = "1.39.2"
  val awssdk        = "2.17.209"
  val betterFiles   = "3.9.1"
  val chimney       = "0.6.2"
  val circe         = "0.14.3"
  val commonsLang3  = "3.12.0"
  val config        = "1.4.2"
  val enumeratum    = "1.7.1"
  val flyway        = "9.8.1"
  val freemarker    = "2.3.31"
  val ical          = "3.2.7"
  val izumi         = "1.0.10"
  val jedis         = "4.2.3"
  val jna           = "5.11.0"
  val junit         = "4.12"
  val kafka         = "3.1.0"
  val mockito       = "3.0.0"
  val mysql         = "8.0.31"
  val newType       = "0.4.4"
  val privo         = "0.0.1-SNAPSHOT"
  val pureConfig    = "0.17.1"
  val quill         = "4.6.0"
  val redisClient   = "3.42"
  val scalaTest     = "3.0.8"
  val scalaUri      = "4.0.2"
  val slf4j         = "1.7.36"
  val sttp          = "3.6.2"

  // Note: Failed to update to 1.2.1 because of tapir-openapi-circe-yaml.
  // Maybe versions don't match exactly anymore, or new version is not finished yet
  val tapir = "0.20.2"

  val testContainers      = "1.17.2"
  val testcontainersScala = "0.40.11"
  val zio                 = "1.0.16" // Note: Updating to 2.x may break compatibility with internal libs
  val zioInteropReactivestreams = "1.3.12"
  val zioKafka                  = "0.17.5"
  val zioMagic                  = "0.3.12"
  val ziosqs                    = "0.4.2"
  val zioHttpVersion = "1.0.0.0-RC29" // Note: Updating to 2.x may break compatibility  with internal libs
}
