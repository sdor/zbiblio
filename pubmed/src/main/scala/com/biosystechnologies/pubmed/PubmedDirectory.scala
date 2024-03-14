package com.biosystechnologies.pubmed

import com.biosystechnologies.pubmed.models.PubmedModels.{DeleteCitation, PubmedArticle, PubmedBookArticle}
import zio.*
import zio.nio.*
import zio.nio.file.*
import zio.stream.*

import java.io.IOException
case class PubmedDirectory(path: Path) extends PubmedStore:

  /** @return
    *   ZStream of path to pubmed files in *.xml.gz format
    */
  def files: ZStream[Any, IOException, Path] =
    Files
      .newDirectoryStream(path)
      .filter { p =>
        p.toString.endsWith(".xml.gz")
      }

  def articles: ZStream[Any, Any, PubmedArticle] = files.flatMap(file =>
    val store = new PubmedFile(file.toFile.toPath)
    store.articles
  )

  def books: ZStream[Any, Any, PubmedBookArticle] = files.flatMap(file =>
    val store = new PubmedFile(file.toFile.toPath)
    store.books
  )

  def deletedCitations: ZStream[Any, Any, DeleteCitation] = files.flatMap(file =>
    val store = new PubmedFile(file.toFile.toPath)
    store.deletedCitations
  )

  override def maxid: ZIO[Any, Any, Int] = {
    for {
      f <- files.run(ZSink.collectAll).map(l => l.maxBy(p => p.filename.toString))
      r <- PubmedFile(f.toFile.toPath).maxid
    } yield r
  }

object PubmedDirectory:
  def live: ZLayer[Path, Nothing, PubmedDirectory] = ZLayer.fromFunction((path: Path) => PubmedDirectory(path))