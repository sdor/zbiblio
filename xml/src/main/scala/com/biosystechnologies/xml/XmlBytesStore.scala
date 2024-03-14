package com.biosystechnologies.xml

import com.fasterxml.aalto.stax.InputFactoryImpl
import com.fasterxml.aalto.{AsyncByteArrayFeeder, AsyncXMLInputFactory, AsyncXMLStreamReader}
import zio.*
import zio.stream.*

import javax.xml.stream.XMLStreamConstants
import scala.annotation.tailrec

object XmlBytesStore {

  import XmlEvents.*
  // TODO: Implement XMLReader configuration


  def xmlParser: ZChannel[Any, Any, Chunk[Byte], Any, Any, Chunk[XmlEvent], Any] = {

    val factory: AsyncXMLInputFactory = new InputFactoryImpl()
    val reader = factory.createAsyncForByteArray()

    lazy val read: ZChannel[Any, Any, Chunk[Byte], Any, Any, Chunk[XmlEvent], Any] = {
      ZChannel.readWith(
        (bytes: Chunk[Byte]) => {
            val arr = bytes.toArray
            reader.getInputFeeder.feedInput(arr, 0, arr.length)
            val events = readEvents(reader)
            ZChannel.write(events) *> read
        },
        (err) => ZChannel.fail(err),
        (done) => ZChannel.succeed(done)
      )
    }
    read
  }


  private def readEvents(reader: AsyncXMLStreamReader[AsyncByteArrayFeeder]): Chunk[XmlEvent] = {
    var chunk = Chunk.empty[XmlEvent]

    @tailrec
    def read: Any = {
        val next = reader.next()
        next match {
          case AsyncXMLStreamReader.EVENT_INCOMPLETE =>
//          case XMLStreamConstants.START_DOCUMENT =>
//            chunk = chunk :+ StartDocument
//            read
//          case XMLStreamConstants.END_DOCUMENT =>
//            chunk = chunk :+ EndDocument
//            read
          case XMLStreamConstants.START_ELEMENT =>
            val attributes = (0 until reader.getAttributeCount).map { i =>
              val optNs =
                Option(reader.getAttributeNamespace(i)).filterNot(_ == "")
              val optPrefix =
                Option(reader.getAttributePrefix(i)).filterNot(_ == "")
              Attribute(
                name = reader.getAttributeLocalName(i),
                value = reader.getAttributeValue(i),
                prefix = optPrefix,
                namespace = optNs
              )
            }.toList
            val namespaces = (0 until reader.getNamespaceCount).map { i =>
              val namespace = reader.getNamespaceURI(i)
              val optPrefix = Option(reader.getNamespacePrefix(i)).filterNot(_ == "")
              Namespace(namespace, optPrefix)
            }.toList
            val optPrefix = Option(reader.getPrefix)
            val optNs = optPrefix.flatMap(prefix =>
              Option(reader.getNamespaceURI(prefix))
            )
            val startElement = StartElement(
              reader.getLocalName,
              attributes,
              optPrefix.filterNot(_ == ""),
              optNs.filterNot(_ == ""),
              namespaceCtx = namespaces
            )
            chunk = chunk :+ startElement
            read
          case XMLStreamConstants.END_ELEMENT =>
            chunk = chunk :+ EndElement(reader.getLocalName)
            read
          case XMLStreamConstants.CHARACTERS =>
            chunk = chunk :+ Characters(reader.getText)
            read
          case XMLStreamConstants.PROCESSING_INSTRUCTION =>
            chunk = chunk :+ ProcessingInstruction(
              Option(reader.getPITarget),
              Option(reader.getPIData)
            )
            read
          case XMLStreamConstants.COMMENT =>
            chunk = chunk :+ Comment(reader.getText)
            read
          case XMLStreamConstants.CDATA =>
            chunk = chunk :+ CData(reader.getText)
            read
          case _ =>
            read
        }
    }
    read
    chunk
  }

}

trait XmlBytesStore extends XmlEventsStore {

  import XmlBytesStore.*
  import XmlEvents.*

  def bytes: ZStream[Any, Any, Byte]

  def events: ZStream[Any, Any, XmlEvent] = bytes.via(ZPipeline.fromChannel(xmlParser))

}
