package pmc

trait TextValue:
  def parts: List[TextPart]
  def text: String = TextValue.makeString(parts)

object TextValue:
  private def makeString(part: TextPart): String =
    part match {
      case TextPart.EMail(email) => email.value
      case TextPart.Title(title) => makeString(title.parts)
      case TextPart.Section(sec) => makeString(sec.parts)
      case TextPart.Paragraph(p) => makeString(p.parts)
      case TextPart.XRef(xref) => makeString(xref.parts)
      case TextPart.Sup(value) => value 
      case TextPart.Sub(value) => value
      case TextPart.Italic(italic) => makeString(italic.parts)
      case TextPart.Bold(bold) => makeString(bold.parts)
      case TextPart.Fig(fig) => makeString(fig.parts)
      case TextPart.Caption(caption) => makeString(caption.parts)
      case _ => ""
    }
  private def makeString(parts: List[TextPart]): String = 
    parts.map(makeString).fold("")(_ + _)
  