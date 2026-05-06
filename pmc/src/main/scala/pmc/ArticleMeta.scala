package pmc

case class ArticleMeta(
    id: List[ArticleId],
    titles: List[Title],
    contributors: List[ArticleContributor],
    affiliations: List[Affiliation],
    pubDates: List[PubDate],
    elocations: List[String],
    volume: Option[String] = None,
    issue: Option[String] = None
)

object ArticleMeta:
  case class ArticleMainTitle(parts: List[TextPart])                               extends Title
  case class ArticleAltTitle(parts: List[TextPart], `type`: Option[String] = None) extends Title
