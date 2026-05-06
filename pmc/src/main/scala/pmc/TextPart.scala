package pmc

enum TextPart:
  case EMail(value: pmc.EMail)
  case Title(value: pmc.Title)
  case Section(value: pmc.Section)
  case Paragraph(value: pmc.Paragraph)
  case XRef(value: pmc.XRef)
  case Sup(value: String)
  case Sub(value: String)
  case Italic(value: pmc.Italic)
  case Bold(bold: pmc.Bold)
  case Fig(fig: Figure)
  case Caption(caption: pmc.Caption)
