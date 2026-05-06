package pmc

case class Affiliation(id: String, institution: String, city: Option[String] = None, country: Option[String], label: Option[String] = None)
