package com.biosystechnologies.pubmed.utils
import com.biosystechnologies.pubmed.PubmedDirectory
import com.biosystechnologies.pubmed.models.PubmedModels.PubmedArticle
import zio.*
import zio.nio.file.*
import zio.stream.ZSink

import java.io.IOException
import java.nio.file.Paths
import scala.io.Source
import zio.json.*
object DumpPubmed extends ZIOAppDefault:
  object EnvLoader {
    def loadEnv(filename: String): Map[String, String] = {
      val source = Source.fromFile(filename)
      val lines = try source.getLines().toSeq finally source.close()

      lines
        .filter(_.trim.nonEmpty)
        .map { line =>
          val parts = line.split('=')
          parts(0).trim -> parts(1).trim
        }
        .toMap
    }
  }

  val env: Map[String, String] = EnvLoader.loadEnv(".env")
  val pathOp: Option[String] = env.get("PUBMED_BASELINE")
  val dir: PubmedDirectory = PubmedDirectory(Path.fromJava(Paths.get(pathOp.get)))
  val sink: ZSink[Any, IOException, PubmedArticle, Nothing, Unit] = ZSink.foreach(article => {
    val js = article.toJson
    Console.printLine(js)
  })

  val app: ZIO[Any, Any, Unit] = {
    for {
      _ <- dir.articles.run(sink)
    } yield {

    }
  }

  def run: ZIO[Any, Any, Unit] = app

