package com.biosystechnologies.pubmed

import com.biosystechnologies.pubmed.models.PubmedModels
import com.biosystechnologies.pubmed.models.PubmedModels.*
import zio.*
import zio.stream.*
import scala.math.*


/*
 * Anything that may return ZStreams of pubmed articles, book and deleted citations
 * */
trait PubmedStore:

  /** @return
    *   ZStream of articles
    */
  def articles: ZStream[Any, Any, PubmedArticle]

  /** @return
    *   ZStream of books
    */
  def books: ZStream[Any, Any, PubmedBookArticle]

  /** @return
    *   ZStream of deleted citations
    */
  def deletedCitations: ZStream[Any, Any, DeleteCitation]

  /** @return
    *   ZStream of pubmed items (articles, books and deleted citations)
    */
  def items: ZStream[Any, Any, PubmedArticle | PubmedBookArticle | DeleteCitation] = articles ++ books ++ deletedCitations

  /** ids()
    *
    * @return
    *   ZStream of pubmed identifiers (PMID values converted into integer)
    */

  def ids: ZStream[Any, Any, Int] = articles.map(_.id) ++ books.map(_.id)

  def maxid: ZIO[Any,Any,Int] = {
    for {
      r <- ids.run(ZSink.collectAll).map(l => l.max)
    } yield r
  }

  /** @return
    *   ZStream of references in particular item
    */
  def references: ZStream[Any, Any, (Int, List[Int])] = articles.map(a => (a.id -> a.references))
