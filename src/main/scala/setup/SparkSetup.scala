package text.similarity
package setup

import org.apache.spark.sql.SparkSession

import java.io.{FileNotFoundException, InputStream}
import java.util.Properties


abstract class SparkSetup(appName: String) {

  val resourceStream: InputStream = getClass.getResourceAsStream("/config.properties")
  val CONFIG: Properties = new Properties()

  if (resourceStream != null) {
    CONFIG.load(resourceStream)
  } else {
    throw new FileNotFoundException("Configuration file could not be loaded")
  }

  val MINIMUM_TOKEN_LENGTH: Int = CONFIG.getProperty("MINIMUM_TOKEN_LENGTH").toInt

  val spark: SparkSession = SparkSession
    .builder()
    .appName(appName)
    .getOrCreate()
  spark.sparkContext.setLogLevel("ERROR")

}