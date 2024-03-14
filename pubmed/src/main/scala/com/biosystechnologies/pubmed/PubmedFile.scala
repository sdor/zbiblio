package com.biosystechnologies.pubmed

import com.biosystechnologies.pubmed.models.PubmedModels.{DeleteCitation, PubmedArticle, PubmedBookArticle}
import com.biosystechnologies.xml.XmlFileStore
import zio.ZLayer
import zio.stream.ZStream

import java.nio.file.Path

case class PubmedFile(path: Path) extends XmlFileStore with PubmedStore:

  def articles: ZStream[Any, Any, PubmedArticle] =
    elements("PubmedArticleSet" :: "PubmedArticle" :: Nil)
    .map[PubmedArticle](e=>e)

  def books: ZStream[Any, Any, PubmedBookArticle] =
    elements("PubmedArticleSet" :: "PubmedBookArticle" :: Nil)
    .map[PubmedBookArticle](e=>e)

  def deletedCitations: ZStream[Any, Any, DeleteCitation] =
    elements("PubmedArticleSet" :: "DeleteCitation" :: Nil)
    .map[DeleteCitation](e=>e)

object PubmedFile:
  def live: ZLayer[Path, Nothing, PubmedFile] = ZLayer.fromFunction((path: Path) => PubmedFile(path))
