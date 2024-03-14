package com.biosystechnologies.xml

object XmlEvents {
  sealed trait XmlEvent
  
  case object IncompleteEvent extends XmlEvent
  case object UnknownEvent extends XmlEvent

  sealed trait TextEvent extends XmlEvent {
    def text: String
  }

  final case class Namespace(uri: String, prefix: Option[String] = None)

  final case class Attribute(name: String,
                             value: String,
                             prefix: Option[String] = None,
                             namespace: Option[String] = None)

  final case class StartElement(localName: String,
                                attributesList: List[Attribute] = List.empty[Attribute],
                                prefix: Option[String] = None,
                                namespace: Option[String] = None,
                                namespaceCtx: List[Namespace] = List.empty[Namespace])
    extends XmlEvent {


    val attributes: Map[String, String] =
      attributesList.map(attr => attr.name -> attr.value).toMap


    def findAttribute(name: String): Option[Attribute] = attributesList.find(_.name == name)

  }

  final case class EndElement(localName: String) extends XmlEvent

  final case class Characters(text: String) extends TextEvent

  final case class ProcessingInstruction(target: Option[String], data: Option[String]) extends XmlEvent

  final case class Comment(text: String) extends XmlEvent

  final case class CData(text: String) extends TextEvent

  case object StartDocument extends XmlEvent

  case object EndDocument extends XmlEvent

  object StartElement {

    def apply(localName: String, attributes: Map[String, String]): StartElement = {
      val attributesList = fromMapToAttributeList()(attributes)
      new StartElement(localName, attributesList, prefix = None, namespace = None, namespaceCtx = List.empty[Namespace])
    }

    def fromMapToAttributeList(prefix: Option[String] = None,
                               namespace: Option[String] = None)(attributes: Map[String, String]): List[Attribute] =
      attributes.toList.map {
        case (name, value) => Attribute(name, value, prefix, namespace)
      }
  }


}
