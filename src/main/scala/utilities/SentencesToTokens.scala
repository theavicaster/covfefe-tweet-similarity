package text.similarity
package utilities

object SentencesToTokens {

  val toTokens = (minTokenLen: Int, stopWords: Array[String], text: String) =>
    text.split("""[^'’\p{L}\p{M}]+""") // words including apostrophe
      .map(w => w.toLowerCase.replaceAll("[^A-Za-z0-9]", "")) // get rid of non-alphanumerics
      .filter(w => w.length > minTokenLen)
      .filter(w => !stopWords.contains(w))

}
