package text.similarity
package processing

import setup.SparkSetup
import utilities.CosineSimilarity.cosineSimilarity
import utilities.SentencesToTokens.toTokens

import org.apache.spark.ml.feature.{HashingTF, IDF, StopWordsRemover}
import org.apache.spark.sql.functions.{col, length, udf}

object SparkTextSimilarity {

  def main(args: Array[String]): Unit = {
    SparkTextSimilarityProcessor("Trump Tweet Similarity")
  }
}

class SparkTextSimilarityProcessor(appName: String)
  extends SparkSetup(appName: String) {

  val trumpTweets = spark.read
    .format("csv")
    .option("header", "true")
    .load("src/main/resources/trump_insult_tweets_2014_to_2021.csv")
    .filter(col("tweet").isNotNull)
    .filter(length(col("tweet")) < 100) // short tweets!
    .dropDuplicates("tweet")
    .withColumnRenamed("_c0", "ID")
    .select("ID", "tweet")


  trumpTweets.show(10, truncate = false)

  val stopWords = StopWordsRemover.loadDefaultStopWords("english")

  val toTokensUDF = udf(toTokens.curried(MINIMUM_TOKEN_LENGTH)(stopWords))
  val filteredTweets = trumpTweets.withColumn("tweetFiltered", toTokensUDF(col("tweet")))

  val hashingTF = new HashingTF()
    .setInputCol("tweetFiltered")
    .setOutputCol("tf")
    .setNumFeatures(1 << 12) // 2^12
    .setBinary(false)
  val tfTokens = hashingTF.transform(filteredTweets)

  val idf = new IDF()
    .setInputCol("tf")
    .setOutputCol("tf_idf")
  val idfModel = idf.fit(tfTokens)
  val tfIdfTokens = idfModel.transform(tfTokens)

  tfIdfTokens.createOrReplaceTempView("i")
  tfIdfTokens.createOrReplaceTempView("j")

  spark.udf.register("cosineSimilarity", cosineSimilarity)

  val ans = spark.sql("SELECT i.tweet AS tweet1, j.tweet AS tweet2, cosineSimilarity(i.tf_idf, j.tf_idf) AS similarity " +
    "FROM i JOIN j " +
    "WHERE i.ID < j.ID " +
    "AND i.tweet <> j.tweet " +
    "ORDER BY similarity DESC ")

  ans.select("tweet1", "tweet2", "similarity")
    .show(25, truncate = false)

  ans.coalesce(1)
    .write
    .format("csv")
    .option("header","true")
    .option("sep","|")
    .mode("overwrite")
    .save("src/main/resources/results")

}

object SparkTextSimilarityProcessor {
  def apply(appName: String): SparkTextSimilarityProcessor =
    new SparkTextSimilarityProcessor(appName)
}
