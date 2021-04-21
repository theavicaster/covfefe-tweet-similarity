# Trump's Tweet Similarity

>I've found myself missing the distinguished gentleman, to be honest. Particularly the words of wisdom we were blessed with on Twitter.
>
>Alas, good things aren't always meant to be. (⌣̩̩́_⌣̩̩̀)

Anyhow, if you've ever wondered if he recycled the same few tweets over and over again, take a look.

## Source of Data
[All Trump's Twitter Insults - Kaggle](https://www.kaggle.com/ayushggarg/all-trumps-twitter-insults-20152021) has the most bang for your buck.

## Similarity Measures

I've used Spark's MLLib to generate [TF-IDF](https://en.wikipedia.org/wiki/Tf%E2%80%93idf) vectors.

[Word2Vec](https://en.wikipedia.org/wiki/Word2vec) seemed to be a much better fit. I averaged the vectors for every word to approximate Doc2Vec.

[Cosine Similarity](https://en.wikipedia.org/wiki/Cosine_similarity) seems fair enough on these vectors.

## Want to Run it Yourself?

We're working with limited data here, but Apache Spark is a natural solution when we might want to get far more data intensive.

Make sure you have Spark 2.4.5 installed and 2.11.12 of Scala.

`sbt package` generates a JAR in `\target`


Run it on Spark with the following -
```
$SPARK_HOME/bin/spark-submit --class text.similarity.processing.SparkTextSimilarity --master local[*] target/scala-2.11/spark-nlp_2.11-0.1.jar
```
Your installation directory will vary

Try using bigger datasets, to scale up and see how powerful Spark is!

## Results

###Word2Vec

|similarity                                                  |tweet1                                                               |tweet2                                                                                  |
|------------------------------------------------------------|---------------------------------------------------------------------|----------------------------------------------------------------------------------------|
|0.9977101076836087                                          |This case had nothing to do with me. Fake News (as usual!). https://t.co/CZnO4GFcco|Fake News! https://t.co/FAZ1vWfoCV                                                      |
|0.9977012969689664                                          |Fake News is so bad for our Country! https://t.co/ZwA8E0URer         |.@CNN IS FAKE NEWS, and all smart people know that! https://t.co/fqykkHxmx5             |
|0.9976990529578686                                          |Fake election results in Nevada, also! https://t.co/l8MDOSlqQ7       |Fake Journalism. So bad for our Country! https://t.co/70j1l8mW9S                        |
|0.9976987430585879                                          |This despite the Fake News and Polls! https://t.co/DksmF8hTr7        |That’s because it is mostly FAKE NEWS! https://t.co/qM5LtOvsH8                          |
|0.9976974524264487                                          |The Fake News of big ratings loser CNN. https://t.co/rYsv90cnvs      |FAKE NEWS! https://t.co/Y8RwjmJ9D1                                                      |
|0.9976884817602416                                          |#CrookedHillary https://t.co/JeXFnO6e3s                              |A total loser! https://t.co/vm3Vv2f9jf                                                  |
|0.9976874372319063                                          |Sleepy Joe is wrong again! https://t.co/BffAhzSDdi                   |Sleepy Joe’s USA! https://t.co/qL0vHoGk0z                                               |
|0.99768411796407                                            |Fake News is so bad for our Country! https://t.co/ZwA8E0URer         |Great Alan. They are Fake News! https://t.co/n7zUY7mzIQ                                 |
|0.9976710595109453                                          |#CrookedHillary Job Application https://t.co/CKXkAlGSiV              |#CrookedHillary https://t.co/vXhcC8PaPy                                                 |
|0.9976699782256274                                          |A total loser! https://t.co/vm3Vv2f9jf                               |https://pbs.twimg.com/media/CZsWhzZWAAAlbyl.jpg                                         |
|0.9976691174533187                                          |Thank you Mark, but the Fake News Media will never say it! https://t.co/e2V05JPmlx|Fake News! https://t.co/pL9gMFvZEn                                                      |
|0.9976644771132536                                          |#CrookedHillary https://t.co/xyoPFJmByp                              |Dirty Cop! https://t.co/xrB4PYTV0J                                                      |
|0.9976641399605605                                          |Real @FoxNews is doing great, Fake News CNN is dead! https://t.co/1p37tPiB3v|More Fake News! https://t.co/URewvfAUDl                                                 |
|0.9976620985723452                                          |They are Fake News Losers! https://t.co/3RHcBZogms                   |Great Alan. They are Fake News! https://t.co/n7zUY7mzIQ                                 |
|0.9976575979549567                                          |The greatest of all time. Fake News! https://t.co/jiWjLrynQW         |Thank you, the very dishonest Fake News Media is out of control! https://t.co/8J7y900VGK|
|0.9976564694179828                                          |But the Fake News will never show this. Thank you! https://t.co/T8grDsDRvJ|FAKE NEWS! https://t.co/Y8RwjmJ9D1                                                      |
|0.9976530440156114                                          |The greatest of all time. Fake News! https://t.co/jiWjLrynQW         |Laura, just another Fake News Report! https://t.co/Kya7NViNRB                           |
|0.997651117230803                                           |More Fake News! https://t.co/URewvfAUDl                              |....The Fake News Media is being laughed at all over the world! https://t.co/TRX8XdaKgg |
|0.9976506965705207                                          |Now spreading Fake News! https://t.co/Xv9d4gqGEJ                     |.@CNN IS FAKE NEWS, and all smart people know that! https://t.co/fqykkHxmx5             |
|0.9976505286373091                                          |The Fake News refuses to report this! https://t.co/OvsdLJQL4u        |They are Fake News Losers! https://t.co/3RHcBZogms                                      |
|0.9976496301590632                                          |WOW, they got caught. End the Witch Hunt now! https://t.co/A5k3u9Rg3D|Thank you Steve. The greatest Witch Hunt in U.S. history! https://t.co/I3bSNVp6gC       |
|0.997648689772979                                           |Fake News! https://t.co/NWaeaG1exu                                   |People have no idea how Fake the Lamestream Media is! https://t.co/qc8c8C7Pin           |
|0.997640529963653                                           |Great Alan. They are Fake News! https://t.co/n7zUY7mzIQ              |.@CNN IS FAKE NEWS, and all smart people know that! https://t.co/fqykkHxmx5             |
|0.9976348915573245                                          |THE TRUTH! The Witch Hunt is dead. Thank you @marcthiessen. https://t.co/myKaSEnbs7|Thank you Steve. The greatest Witch Hunt in U.S. history! https://t.co/I3bSNVp6gC       |
|0.9976228083054157                                          |The greatest of all time. Fake News! https://t.co/jiWjLrynQW         |But the Fake News will never show this. Thank you! https://t.co/T8grDsDRvJ              |
|0.9976129631306196                                          |#CrookedHillary #ThrowbackThursday https://t.co/v8J0r64J7h           |#CrookedHillary https://t.co/vXhcC8PaPy                                                 |
|0.9976115216390922                                          |The greatest of all time. Fake News! https://t.co/jiWjLrynQW         |The Fake News of big ratings loser CNN. https://t.co/rYsv90cnvs                         |
|0.9976102051089027                                          |....The Fake News Media is being laughed at all over the world! https://t.co/TRX8XdaKgg|Fake News! https://t.co/OedkKwkcd0                                                      |
|0.9976101944516376                                          |Mini Mike, you’re easy! https://t.co/rxFiqSB9RQ https://t.co/G2M1QHj0HV|...And Sleepy Joe! https://t.co/XvsYuVh1bN                                              |
|0.9976091670562546                                          |Witch Hunt! https://t.co/JIJk7l5TAX                                  |Thank you Steve. The greatest Witch Hunt in U.S. history! https://t.co/I3bSNVp6gC       |
|0.9976076097790256                                          |Rigged Election! https://t.co/agmPI8kkRV                             |This is happening all over the U.S. Rigged Election??? https://t.co/MG0d9xUH1Z          |
|0.9976059454638563                                          |Fake election results in Nevada, also! https://t.co/l8MDOSlqQ7       |The Fake Newspaper! https://t.co/X6LEqpQeBc                                             |
|0.9976059153079335                                          |WITCH HUNT!                                                          |A TOTAL WITCH HUNT!!!                                                                   |
|0.9976020298158625                                          |They are Fake News Losers! https://t.co/3RHcBZogms                   |That’s because it is mostly FAKE NEWS! https://t.co/qM5LtOvsH8                          |
|0.9976019708155706                                          |The Fake News refuses to report this! https://t.co/OvsdLJQL4u        |AP has long been Fake News! https://t.co/dmjxcS0tTK                                     |
|0.9976007113322456                                          |Real @FoxNews is doing great, Fake News CNN is dead! https://t.co/1p37tPiB3v|Fake News! https://t.co/FAZ1vWfoCV                                                      |
|0.9975998792603055                                          |Real @FoxNews is doing great, Fake News CNN is dead! https://t.co/1p37tPiB3v|Fake News! https://t.co/pL9gMFvZEn                                                      |
|0.9975974184262963                                          |The Fake News of big ratings loser CNN. https://t.co/rYsv90cnvs      |Fake News will never change! https://t.co/AMX2jzMoXJ                                    |

### TF-IDF

|similarity                                                  |tweet1                                                               |tweet2                                                                                  |
|------------------------------------------------------------|---------------------------------------------------------------------|----------------------------------------------------------------------------------------|
|1.0                                                         |PRESIDENTIAL HARASSMENT!                                             |Presidential Harassment!                                                                |
|1.0                                                         |Fake News!! https://t.co/BG62YbD7Fw                                  |More Fake News! https://t.co/8P2Kax00s7                                                 |
|0.9999999999999998                                          |FAKE NEWS, THE ENEMY OF THE PEOPLE!                                  |FAKE NEWS IS THE ENEMY OF THE PEOPLE!                                                   |
|0.9999999999999998                                          |FAKE NEWS - THE ENEMY OF THE PEOPLE!                                 |FAKE NEWS, THE ENEMY OF THE PEOPLE!                                                     |
|0.9999999999999998                                          |FAKE NEWS - THE ENEMY OF THE PEOPLE!                                 |FAKE NEWS IS THE ENEMY OF THE PEOPLE!                                                   |
|0.8231527407873283                                          |The Greatest Witch Hunt in American History! https://t.co/sPnloffJMT |The Greatest Witch Hunt In American History!                                            |
|0.7819362521018973                                          |WITCH HUNT!                                                          |A TOTAL WITCH HUNT!!!                                                                   |
|0.7641458775699438                                          |FAKE NEWS - THE ENEMY OF THE PEOPLE!                                 |Fake News is truly the ENEMY OF THE PEOPLE!                                             |
|0.7641458775699438                                          |Fake News is truly the ENEMY OF THE PEOPLE!                          |FAKE NEWS, THE ENEMY OF THE PEOPLE!                                                     |
|0.7641458775699438                                          |Fake News is truly the ENEMY OF THE PEOPLE!                          |FAKE NEWS IS THE ENEMY OF THE PEOPLE!                                                   |
|0.7450816055062841                                          |Hillary Clinton failed all over the world.                           |HILLARY FAILED ALL OVER THE WORLD. #BigLeagueTruth                                      |
|0.7420824949863177                                          |\                                                                    |PAY TO PLAY POLITICS.                                                                   |
|0.7378779438598738                                          |The Obama/Biden Administration is the most corrupt Administration in the history of our Country!|The Obama/Biden Administration is the most CORRUPT in HISTORY! https://t.co/kWa3SZnqPY  |
|0.7346505488043752                                          |....The greatest Witch Hunt in political history!                    |The Witch Hunt is the greatest political scam in U.S. history! #MAGA https://t.co/dKExRVOFJt|
|0.7209177396057842                                          |FAKE NEWS - THE ENEMY OF THE PEOPLE!                                 |Fake News Equals the Enemy of the People!                                               |
|0.7209177396057842                                          |Fake News Equals the Enemy of the People!                            |FAKE NEWS, THE ENEMY OF THE PEOPLE!                                                     |
|0.7209177396057842                                          |Fake News Equals the Enemy of the People!                            |FAKE NEWS IS THE ENEMY OF THE PEOPLE!                                                   |

