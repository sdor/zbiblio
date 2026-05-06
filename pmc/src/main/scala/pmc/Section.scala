package pmc

case class Section(parts: List[TextPart], id: Option[String] = None, `sec-type`: Option[String] = None, `disp-level`: Option[Int] = None)
    extends TextValue
  
