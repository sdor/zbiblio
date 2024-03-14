package com.biosystechnologies.xml


import org.w3c.dom.{Document, Element}
import zio.*
import zio.stream.*

import javax.xml.parsers.DocumentBuilderFactory
import scala.collection.mutable


object XmlEventsStore {

  import XmlEvents.*

  private def transform(path: List[String]) = {
    val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()

    def createElement(start: StartElement): Element = {
      val element = start.namespace match {
        case Some(ns) => doc.createElementNS(ns, start.localName)
        case None => doc.createElement(start.localName)
      }
      start.attributes.foreach {
        case (name, value) =>
          element.setAttribute(name, value)
      }
      element
    }

    val matching = path.reverse

    (state: mutable.Stack[Element], event: XmlEvent) => {
      (state, event) match {
        case (stack, StartElement(localName, attributesList, prefix, namespace, namespaceCtx)) =>
          val el = createElement(StartElement(localName, attributesList, prefix, namespace, namespaceCtx))
          stack.push(el)
          (stack, None)
        case (stack, Characters(text)) if stack.nonEmpty =>
          val el = stack.pop()
          el.appendChild(doc.createTextNode(text))
          stack.push(el)
          (stack, None)
        case (stack, CData(text)) if stack.nonEmpty =>
          val el = stack.pop()
          el.appendChild(doc.createCDATASection(text))
          stack.push(el)
          (stack, None)
        case (stack, EndElement(_)) =>
          if (matching.equals(stack.map(_.getTagName).toList)) {
            val el = stack.pop
            (stack, Some(el))
          } else if (stack.length > matching.length) {
            val child = stack.pop
            val parent = stack.pop
            parent.appendChild(child)
            stack.push(parent)
            (stack, None)
          } else {
            stack.pop
            (stack, None)
          }
        case (stack, _) =>
          (stack, None)
      }
    }
  }

  private def subtree(path: List[String]): ZPipeline[Any, Nothing, XmlEvent, Element] = ZPipeline
    .mapAccum[XmlEvent,mutable.Stack[Element],Option[Element]](mutable.Stack.empty[Element])(transform(path))
      .filter(_.isDefined).map(_.get)
}

trait XmlEventsStore {
  import XmlEvents.*
  import XmlEventsStore.*
  def events: ZStream[Any,Any,XmlEvent]

  def elements(path: List[String]): ZStream[Any, Any, Element] = events.via(subtree(path))
}
