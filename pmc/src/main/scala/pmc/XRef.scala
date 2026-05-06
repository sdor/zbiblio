package pmc

case class XRef(rid: String, `type`: String, parts: List[TextPart]) extends TextValue
