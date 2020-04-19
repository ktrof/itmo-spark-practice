import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._


object Main {

  def main(args: Array[String]): Unit = {

    // only for Windows OS
    //System.setProperty("hadoop.home.dir", "C:\\hadoop")

    val sparkSession = SparkSession.builder().master("local[*]").appName("Spark practice").getOrCreate()

    sparkSession.read.text("build.sbt")
      .withColumn("value", explode(split(col("value"), " ")))
      .groupBy("value").count()
      .sort(col("count").desc)
      .show(10, truncate = false)
    Thread.sleep(1000000)
  }

}
