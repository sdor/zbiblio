package pmc

trait ArticleContributor:
  def surname: String
  def givenName: String
  def xref: Option[XRef]
  
