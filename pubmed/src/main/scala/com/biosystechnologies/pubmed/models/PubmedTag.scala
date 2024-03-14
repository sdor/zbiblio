package com.biosystechnologies.pubmed.models

sealed trait PubmedTag:
  def tag: String

object PubmedTag:
  case object PubmedArticle extends PubmedTag { val tag = "PubmedArticle" }
  case object PubmedBookArticle extends PubmedTag { val tag = "PubmedBookArticle" }
  case object DeleteCitation extends PubmedTag { val tag = "DeleteCitation" }
  case object MedlineCitation extends PubmedTag { val tag = "MedlineCitation" }
  case object PubmedData extends PubmedTag { val tag = "PubmedData" }
  case object BookDocument extends PubmedTag { val tag = "BookDocument" }
  case object PubmedBookData extends PubmedTag { val tag = "PubmedBookData" }
  case object PMID extends PubmedTag { val tag = "PMID" }
  case object Book extends PubmedTag { val tag = "Book" }
  case object LocationLabel extends PubmedTag { val tag = "LocationLabel" }
  case object PublicationType extends PubmedTag { val tag = "PublicationType" }
  case object Sections extends PubmedTag { val tag = "Sections" }
  case object ContributionDate extends PubmedTag { val tag = "ContributionDate" }
  case object ItemList extends PubmedTag { val tag = "ItemList" }
  case object DateCompleted extends PubmedTag { val tag = "DateCompleted" }
  case object DateRevised extends PubmedTag { val tag = "DateRevised" }
  case object Article extends PubmedTag { val tag = "Article" }
  case object MedlineJournalInfo extends PubmedTag { val tag = "MedlineJournalInfo" }
  case object ChemicalList extends PubmedTag { val tag = "ChemicalList" }
  case object SupplMeshList extends PubmedTag { val tag = "SupplMeshList" }
  case object CitationSubset extends PubmedTag { val tag = "CitationSubset" }
  case object CommentsCorrectionsList extends PubmedTag { val tag = "CommentsCorrectionsList" }
  case object CommentsCorrections extends PubmedTag { val tag = "CommentsCorrections" }
  case object GeneSymbolList extends PubmedTag { val tag = "GeneSymbolList" }
  case object GeneSymbol extends PubmedTag { val tag = "GeneSymbol" }
  case object MeshHeadingList extends PubmedTag { val tag = "MeshHeadingList" }
  case object NumberOfReferences extends PubmedTag { val tag = "NumberOfReferences" }
  case object PersonalNameSubjectList extends PubmedTag { val tag = "PersonalNameSubjectList" }
  case object PersonalNameSubject extends PubmedTag { val tag = "PersonalNameSubject" }

  case object Identifier extends PubmedTag {
    val tag = "Identifier"
  }
  case object SectionTitle extends PubmedTag {
    val tag = "SectionTitle"
  }

  case object Section extends PubmedTag {
    val tag = "Section"
  }

  case object Season extends PubmedTag {
    val tag = "Season"
  }

  case object Item extends PubmedTag {
    val tag = "Item"
  }

  case object OtherID extends PubmedTag { val tag = "OtherID" }
  case object OtherAbstract extends PubmedTag { val tag = "OtherAbstract" }
  case object KeywordList extends PubmedTag { val tag = "KeywordList" }
  case object CoiStatement extends PubmedTag { val tag = "CoiStatement" }
  case object SpaceFlightMission extends PubmedTag { val tag = "SpaceFlightMission" }
  case object InvestigatorList extends PubmedTag { val tag = "InvestigatorList" }
  case object Investigator extends PubmedTag { val tag = "Investigator" }
  case object GeneralNote extends PubmedTag { val tag = "GeneralNote" }
  case object History extends PubmedTag { val tag = "History" }
  case object PublicationStatus extends PubmedTag { val tag = "PublicationStatus" }
  case object ObjectList extends PubmedTag { val tag = "ObjectList" }
  case object ReferenceList extends PubmedTag { val tag = "ReferenceList" }
  case object Journal extends PubmedTag { val tag = "Journal" }
  case object ArticleTitle extends PubmedTag { val tag = "ArticleTitle" }
  case object Pagination extends PubmedTag { val tag = "Pagination" }
  case object ELocationID extends PubmedTag { val tag = "ELocationID" }
  case object Abstract extends PubmedTag { val tag = "Abstract" }
  case object AuthorList extends PubmedTag { val tag = "AuthorList" }
  case object Language extends PubmedTag { val tag = "Language" }
  case object DataBankList extends PubmedTag { val tag = "DataBankList" }
  case object GrantList extends PubmedTag { val tag = "GrantList" }
  case object PublicationTypeList extends PubmedTag { val tag = "PublicationTypeList" }
  case object VernacularTitle extends PubmedTag { val tag = "VernacularTitle" }
  case object ArticleDate extends PubmedTag { val tag = "ArticleDate" }
  case object AbstractText extends PubmedTag { val tag = "AbstractText" }
  case object CopyrightInformation extends PubmedTag { val tag = "CopyrightInformation" }
  case object AccessionNumber extends PubmedTag { val tag = "AccessionNumber" }
  case object Acronym extends PubmedTag { val tag = "Acronym" }
  case object Affiliation extends PubmedTag { val tag = "Affiliation" }
  case object AffiliationInfo extends PubmedTag { val tag = "AffiliationInfo" }
  case object Agency extends PubmedTag { val tag = "Agency" }
  case object Year extends PubmedTag { val tag = "Year" }
  case object Month extends PubmedTag { val tag = "Month" }
  case object Day extends PubmedTag { val tag = "Day" }
  case object ArticleId extends PubmedTag { val tag = "ArticleId" }
  case object ArticleIdList extends PubmedTag { val tag = "ArticleIdList" }
  case object LastName extends PubmedTag { val tag = "LastName" }
  case object ForeName extends PubmedTag { val tag = "ForeName" }
  case object Initials extends PubmedTag { val tag = "Initials" }
  case object Suffix extends PubmedTag { val tag = "Suffix" }
  case object CollectiveName extends PubmedTag { val tag = "CollectiveName" }
  case object Publisher extends PubmedTag { val tag = "Publisher" }
  case object BookTitle extends PubmedTag { val tag = "BookTitle" }
  case object PubDate extends PubmedTag { val tag = "PubDate" }
  case object BeginningDate extends PubmedTag { val tag = "BeginningDate" }
  case object EndingDate extends PubmedTag { val tag = "EndingDate" }
  case object Volume extends PubmedTag { val tag = "Volume" }
  case object VolumeTitle extends PubmedTag { val tag = "VolumeTitle" }
  case object Edition extends PubmedTag { val tag = "Edition" }
  case object CollectionTitle extends PubmedTag { val tag = "CollectionTitle" }
  case object Isbn extends PubmedTag { val tag = "Isbn" }
  case object Medium extends PubmedTag { val tag = "Medium" }
  case object ReportNumber extends PubmedTag { val tag = "ReportNumber" }
  case object RegistryNumber extends PubmedTag { val tag = "RegistryNumber" }
  case object NameOfSubstance extends PubmedTag { val tag = "NameOfSubstance" }
  case object DataBankName extends PubmedTag { val tag = "DataBankName" }
  case object AccessionNumberList extends PubmedTag { val tag = "AccessionNumberList" }
  case object JournalIssue extends PubmedTag { val tag = "JournalIssue" }
  case object MedlineDate extends PubmedTag { val tag = "MedlineDate" }
  case object Reference extends PubmedTag { val tag = "Reference" }
