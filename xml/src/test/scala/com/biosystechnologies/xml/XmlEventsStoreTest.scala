package com.biosystechnologies.xml

import com.biosystechnologies.xml.XmlEvents._
import org.w3c.dom.Element
import zio.Scope
import zio.stream.ZSink
import zio.test.{Spec, TestEnvironment, ZIOSpecDefault, assertTrue}

import java.nio.file.{Path, Paths}

object XmlEventsStoreTest extends ZIOSpecDefault {
  def spec: Spec[TestEnvironment with Scope, Any] = suite("XmlEventsStore")(
    test("events") {
      val store = new XmlFileStore:
        def path: Path = Paths.get("test-data", "articleset.xml")
      for {
        result <- store.events.run(ZSink.collectAll[XmlEvent])
      } yield {
        val first = result.head
        val last = result.last
        assertTrue(first match { case StartElement(localName, attributesList, prefix, namespace, namespaceCtx) if localName == "PubmedArticleSet" => true case _ => false })
        assertTrue(last  match { case EndElement(localName) if localName == "PubmedArticleSet" => true case _ => false })
      }
    },
    test("subtree") {
      val store = new XmlFileStore:
        def path: Path = Paths.get("test-data", "articleset.xml")
      for {
        result <- store.elements("PubmedArticleSet" :: "PubmedArticle" :: Nil).run(ZSink.collectAll[Element])
      } yield {
        assertTrue(result.length == 4)
      }
    }
  )
}
