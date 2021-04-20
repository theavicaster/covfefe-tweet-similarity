name := "spark-nlp"

version := "0.1"

scalaVersion := "2.11.12"

idePackagePrefix := Some("text.similarity")

libraryDependencies ++= {
  val sparkVersion = "2.4.5"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
    "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
    "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided",
  )
}
