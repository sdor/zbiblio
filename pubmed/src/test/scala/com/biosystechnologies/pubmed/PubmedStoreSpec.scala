package com.biosystechnologies.pubmed
import zio.*
import zio.nio.*
import zio.nio.file.*
import zio.stream.*
import zio.test.*
import zio.test.Assertion.*

object PubmedStoreSpec extends ZIOSpecDefault:
  val store = new PubmedDirectory(Path("test-data"))
  val file  = new PubmedFile(java.nio.file.Path.of("test-data", "articleset.xml.gz"))
  def spec: Spec[TestEnvironment with Scope, Any] =
    suite("PubmedStore")(
      suite("PubmedFile")(
        test("parse pubmed sample file ") {
          val f = new PubmedFile(java.nio.file.Path.of("test-data", "sample-0001.xml.gz"))
          for {
            count <- f.items.run(ZSink.count)
          } yield assertTrue(count == 21109)
        },
        test("references") {
          for references <- file.references.run(ZSink.collectAll)
          yield
            assertTrue(references.size == 4)
            val computed: List[Int] = references
              .find {
                case (28589863, _) => true
                case _             => false
              }
              .map { case (_, l) => l }
              .getOrElse(List.empty)
            val expected = List(11932250, 12529312, 11934743, 17048387, 2231712, 16819802, 14759262, 18227115, 24493737, 9254694, 21209072)
            assertTrue(computed == expected)
        },
        test("maxid") {
          for max <- file.maxid
          yield assertTrue(max == 28895354)
        }
      ),
      suite("PubmedDirectory")(
        test("files") {
          for files <- store.files.run(ZSink.collectAll)
          yield assert(files)(Assertion.equalTo(Chunk(Path("test-data", "sample-0001.xml.gz"), Path("test-data", "articleset.xml.gz"))))
        },
        test("maxid") {
          for max <- store.maxid
          yield assertTrue(max == 37733308)
        },
        test("references") {
          for references <- store.references.run(ZSink.collectAll)
          yield {
            val computed = references
              .find {
                case (28589863, _) => true
                case _             => false
              }
              .map { case (_, l) => l }
              .getOrElse(List.empty)
            val expected = List(11932250, 12529312, 11934743, 17048387, 2231712, 16819802, 14759262, 18227115, 24493737, 9254694, 21209072)
            assertTrue(
              computed == expected
            )
          }
        }
      )
    )
