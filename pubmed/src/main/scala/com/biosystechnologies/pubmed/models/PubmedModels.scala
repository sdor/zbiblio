package com.biosystechnologies.pubmed.models

import org.w3c.dom.{Element, Node}
import zio.json.{DeriveJsonCodec, JsonCodec}

import scala.collection.mutable
import scala.reflect.ClassTag

object PubmedModels:
  case class PMID(version: String, value: String)

  case class Title(value: String)

  case class ISOAbbreviation(value: String)

  case class Journal(
      issn: Option[ISSN],
      journalIssue: JournalIssue,
      title: Option[Title],
      abbreviation: Option[ISOAbbreviation]
  )

  case class ISSN(issnType: String, value: String)

  case class JournalIssue(
      citedMedium: String,
      volume: Option[Volume],
      issue: Option[Issue],
      pubDate: PubDate
  )

  case class Volume(value: String)

  case class Issue(value: String)

  case class PubDate(year: Option[Year], medlineDate: Option[MedlineDate])

  case class MedlineDate(value: String)

  case class ArticleTitle(value: String)

  case class AbstractText(
      label: Option[String],
      nlmCategory: Option[String],
      value: String
  )

  case class CopyrightInformation(value: String)

  case class Abstract(
      abstractText: Option[List[AbstractText]],
      copyrightInformation: Option[CopyrightInformation]
  )

  case class MedlinePgn(value: String)

  case class Pagination(medlinePgn: Option[MedlinePgn])

  case class LastName(value: String)

  case class ForeName(value: String)

  case class Initials(value: String)

  case class CollectiveName(value: String)

  case class Author(
      validYN: Option[String],
      lastName: Option[LastName],
      foreName: Option[ForeName],
      initials: Option[Initials],
      collectiveName: Option[CollectiveName],
      affiliationInfo: Option[List[AffiliationInfo]]
  )

  case class AffiliationInfo(affiliation: Option[List[Affiliation]])

  case class Affiliation(value: String)

  case class AuthorList(validYN: Option[String], author: List[Author])

  case class Language(value: String)

  case class PublicationType(ui: String, value: String)

  case class PublicationTypeList(publicationType: List[PublicationType])

  case class ELocationID(
      eIdtype: String,
      validYN: Option[String],
      value: String
  )

  case class GrantID(value: String)

  case class Acronym(value: String)

  case class Agency(value: String)

  case class Grant(
      grantID: Option[GrantID],
      acronym: Option[Acronym],
      agency: Option[Agency],
      country: Option[Country]
  )

  case class GrantList(completeYN: Option[String], grant: List[Grant])

  case class VernacularTitle(value: String)

  case class ArticleDate(
      year: Year,
      month: Month,
      day: Day,
      dateType: Option[String]
  )

  case class Article(
      pubModel: String,
      journal: Journal,
      articleTitle: Option[ArticleTitle],
      pagination: Option[Pagination],
      elocationID: Option[List[ELocationID]],
      `abstract`: Option[Abstract],
      authorList: Option[AuthorList],
      language: List[Language],
      dataBankList: Option[DataBankList],
      grantList: Option[GrantList],
      publicationTypeList: Option[PublicationTypeList],
      vernacularTitle: Option[VernacularTitle],
      articleDate: Option[List[ArticleDate]]
  )

  case class Country(value: String)

  case class MedlineTA(value: String)

  case class NlmUniqueID(value: String)

  case class ISSNLinking(value: String)

  case class MedlineJournalInfo(
      country: Option[Country],
      medlineTA: Option[MedlineTA],
      nlmUniqueID: NlmUniqueID,
      issnLinking: Option[ISSNLinking]
  )

  case class DescriptorName(
      ui: String,
      majorTopicYN: Option[String],
      `type`: Option[String],
      value: String
  )

  case class QualifierName(
      ui: String,
      majorTopicYN: Option[String],
      value: String
  )

  case class MeshHeading(
      descriptorName: DescriptorName,
      qualifierName: Option[List[QualifierName]]
  )

  case class MeshHeadingList(meshHeading: List[MeshHeading])

  case class RegistryNumber(value: String)

  case class NameOfSubstance(ui: String, value: String)

  case class Chemical(
      registryNumber: RegistryNumber,
      nameOfSubstance: NameOfSubstance
  )

  case class ChemicalList(chemical: List[Chemical])

  // <ELocationID EIdType="doi" ValidYN="Y"]10.1093/ndt/gfw079</ELocationID]

  case class Keyword(majorTopicYN: Option[String], value: String)

  case class KeywordList(owner: Option[String], keyword: Option[List[Keyword]])

  case class DataBankName(value: String)

  case class AccessionNumber(value: String)

  case class AccessionNumberList(
      accessionNumber: Option[List[AccessionNumber]]
  )

  case class DataBank(
      dataBankName: Option[DataBankName],
      accessionNumberList: AccessionNumberList
  )

  case class DataBankList(completeYN: Option[String], dataBank: List[DataBank])

  case class Year(value: String)

  case class Month(value: String)

  case class Day(value: String)

  case class DateCompleted(year: Year, month: Month, day: Day)

  case class DateRevised(year: Year, month: Month, day: Day)

  case class SupplMeshName(
      value: String,
      `type`: String,
      suppMeshName: Option[String],
      ui: String
  )
  //  object Year:
  //    given fromElement: Conversion[Element, Year] = (e: Element) =>
  //      Year(value = e.getTextContent)

  case class SupplMeshList(supplMeshName: List[SupplMeshName])

  case class CitationSubset(value: String)

  case class RefSource(value: String)

  case class Note(value: String)

  case class CommentsCorrections(
      refType: String,
      refSource: RefSource,
      pmid: Option[PMID],
      note: Option[Note]
  )

  case class CommentsCorrectionsList(
      commentsCorrections: Option[List[CommentsCorrections]]
  )

  case class GeneSymbol(value: String)

  case class GeneSymbolList(geneSymbol: Option[List[GeneSymbol]])

  case class PersonalNameSubject(
      lastName: LastName,
      foreName: Option[ForeName],
      initials: Option[Initials],
      suffix: Option[Suffix]
  )

  case class PersonalNameSubjectList(
      personalNameSubject: Option[List[PersonalNameSubject]]
  )

  case class OtherID(source: String, value: String)

  case class OtherAbstract(
      `type`: String,
      language: Option[String],
      abstractText: Option[List[AbstractText]],
      copyrightInformation: Option[CopyrightInformation]
  )

  case class NumberOfReferences(value: String)

  case class MedlineCitation(
      status: String,
      versionID: Option[String],
      versionDate: Option[String],
      indexingMethod: Option[String],
      owner: Option[String],
      pmid: PMID,
      dateCompleted: Option[DateCompleted],
      dateRevised: Option[DateRevised],
      article: Article,
      medlineJournalInfo: MedlineJournalInfo,
      chemicalList: Option[ChemicalList],
      supplMeshList: Option[SupplMeshList],
      citationSubset: Option[List[CitationSubset]],
      commentsCorrectionsList: Option[CommentsCorrectionsList],
      geneSymbolList: Option[GeneSymbolList],
      meshHeadingList: Option[MeshHeadingList],
      numberOfReferences: Option[NumberOfReferences],
      personalNameSubjectList: Option[PersonalNameSubjectList],
      otherID: Option[List[OtherID]],
      otherAbstract: Option[List[OtherAbstract]],
      keywordList: Option[List[KeywordList]]
  ):
    def id: Int = pmid.value.toInt

  /** @param idType
    *   doi | pii | pmcpid | pmpid | pmc | mid | sici | pubmed | medline | pmcid | pmcbook | bookaccession | pubmed
    * @param value
   *    string value of pubmed tag
    */
  case class ArticleId(idType: String, value: String):
    def isPubmedId: Boolean = idType match
      case "pubmed" => true
      case _        => false

  case class Citation(value: String)

  case class ArticleIdList(articleId: Option[List[ArticleId]]):
    /** @return
      *   list of article id values converted to integer where id type is 'pubmed'
      */
    def pubmed_ids: List[Int] = articleId
      .map { ids =>
        ids.filter(_.isPubmedId).map(_.value.toInt)
      }
      .getOrElse(List.empty[Int])

  case class Reference(citation: Option[Citation], articleIdList: Option[ArticleIdList]):
    /** @return
      *   list of article id values converted to integer where id type is 'pubmed'
      */
    def pubmed_ids: List[Int] = articleIdList.map(_.pubmed_ids).getOrElse(List.empty[Int])

  case class ReferenceList(title: Option[Title], reference: Option[List[Reference]], referenceList: Option[List[ReferenceList]]):
    /** @return
      *   list of article id values converted to integer where id type is 'pubmed'
      */
    def pubmed_ids: List[Int] =
      val reference_ids: List[Int]      = reference.map(_.map(_.pubmed_ids)).getOrElse(List.empty).flatten
      val reference_list_ids: List[Int] = referenceList.map(_.map(_.pubmed_ids)).getOrElse(List.empty).flatten
      reference_ids ++ reference_list_ids

  case class PublicationStatus(value: String)

  case class Hour(value: String)

  case class Minute(value: String)

  case class Second(value: String)

  case class PubMedPubDate(
      pubStatus: String,
      year: Year,
      month: Month,
      day: Day,
      hour: Option[Hour],
      minute: Option[Minute],
      second: Option[Second]
  )

  case class History(pubMedPubDate: List[PubMedPubDate])

  case class Param(`type`: String, value: String)

  case class Object(`type`: String, param: Option[List[Param]])

  case class ObjectList(`object`: List[Object])

  case class PubmedData(
      history: Option[History],
      referenceList: Option[List[ReferenceList]],
      publicationStatus: PublicationStatus,
      articleIdList: ArticleIdList,
      objectList: Option[ObjectList]
  ):

    /**
     * @return
     *  list of article ids
     */
    def ids: List[ArticleId] = articleIdList.articleId match {
      case Some(l) => l
      case _ => List.empty
    }

    /** @return
     *   list of article id values converted to integer where id type is 'pubmed'. The list represents pubmed articles ids referenced by pubmed article
     */
    def references: List[Int] = referenceList.map(
      reference_list =>
        reference_list.flatMap {
          l =>
            l.pubmed_ids
        }
    ).getOrElse(List.empty)

  case class PubmedArticle(
      medlineCitation: MedlineCitation,
      pubmedData: Option[PubmedData]
  ):

    /** id()
      *
      * @return
      *   integer value of PIMD of the article
      */
    def id: Int = medlineCitation.id

    /** reference()
      *
      * @return
      *   list of integer values of pubmed ids referenced by the article
      */
    def references: List[Int] = pubmedData.map(_.references).getOrElse(List.empty)

  case class PublisherName(value: String)

  case class PublisherLocation(value: String)

  case class Publisher(
      publisherName: PublisherName,
      publisherLocation: Option[PublisherLocation]
  )

  case class BookTitle(value: String)

  case class Season(value: String)

  case class EndingDate(
      year: Year,
      month: Month,
      day: Option[Day],
      season: Option[Season]
  )

  case class BeginningDate(
      year: Year,
      month: Month,
      day: Option[Day],
      season: Option[Season]
  )

  case class Suffix(value: String)

  case class Identifier(source: String, value: String)

  case class Investigator(
      lastName: LastName,
      foreName: Option[ForeName],
      initials: Option[Initials],
      suffix: Option[Suffix],
      identifier: Option[List[Identifier]],
      affiliationInfo: Option[List[AffiliationInfo]]
  )

  case class InvestigatorList(investigator: List[Investigator])

  case class VolumeTitle(value: String)

  case class CollectionTitle(value: String)

  case class Edition(value: String)

  case class Medium(value: String)

  case class Isbn(value: String)

  case class ReportNumber(value: String)

  case class Book(
      publisher: Publisher,
      bookTitle: BookTitle,
      pubDate: PubDate,
      beginningDate: Option[BeginningDate],
      endingDate: Option[EndingDate],
      authorList: Option[List[AuthorList]],
      investigatorList: Option[InvestigatorList],
      volume: Option[Volume],
      volumeTitle: Option[VolumeTitle],
      edition: Option[Edition],
      collectionTitle: Option[CollectionTitle],
      isbn: Option[List[Isbn]],
      elocationID: Option[List[ELocationID]],
      medium: Option[Medium],
      reportNumber: Option[ReportNumber]
  )

  case class LocationLabel(`type`: Option[String], value: String)

  case class SectionTitle(value: String)

  case class Section(
      locationLabel: Option[LocationLabel],
      sectionTitle: SectionTitle,
      section: Option[List[Section]]
  )

  case class Item(value: String)

  case class ItemList(item: List[Item])

  case class Sections(section: List[Section])

  case class ContributionDate(
      year: Year,
      month: Option[Month],
      day: Option[Day],
      season: Option[Season]
  )

  case class BookDocument(
      pmid: PMID,
      articleIdList: ArticleIdList,
      book: Book,
      locationLabel: Option[List[LocationLabel]],
      articleTitle: Option[ArticleTitle],
      vernacularTitle: Option[VernacularTitle],
      pagination: Option[Pagination],
      language: Option[List[Language]],
      authorList: Option[List[AuthorList]],
      investigatorList: Option[InvestigatorList],
      publicationType: Option[List[PublicationType]],
      `abstract`: Option[Abstract],
      sections: Option[Sections],
      keywordList: Option[List[KeywordList]],
      contributionDate: Option[ContributionDate],
      dateRevised: Option[DateRevised],
      grantList: Option[GrantList],
      itemList: Option[List[ItemList]],
      referenceList: Option[List[ReferenceList]]
  ):
    def id: Int = pmid.value.toInt

  case class PubmedBookData(
      history: Option[History],
      publicationStatus: PublicationStatus,
      articleIdList: ArticleIdList,
      objectList: Option[ObjectList]
  )

  case class PubmedBookArticle(
      bookDocument: BookDocument,
      pubmedBookData: Option[PubmedBookData]
  ):
    def id: Int = bookDocument.id

  case class DeleteCitation(pmid: List[PMID])

  case class DeleteDocument(pmid: Option[List[PMID]])

  given Conversion[Element, Keyword] = (e: Element) =>
    Keyword(
      value = e.getTextContent,
      majorTopicYN = opAttribute(e, "MajorTopicYN")
    )
  
  given Conversion[Element, KeywordList] = (e: Element) =>
    KeywordList(
      keyword = opArray(e, "Keyword"),
      owner = opAttribute(e, "Owner")
    )
  
  given Conversion[Element, DataBankName] = (e: Element) => DataBankName(value = e.getTextContent)
  
  given Conversion[Element, AccessionNumber] = (e: Element) => AccessionNumber(value = e.getTextContent)
  
  given JsonCodec[AccessionNumber] = DeriveJsonCodec.gen[AccessionNumber]
  
  given Conversion[Element, AccessionNumberList] =
    (e: Element) => AccessionNumberList(accessionNumber = opArray(e, "AccessionNumber"))

  given JsonCodec[AccessionNumberList] = DeriveJsonCodec.gen[AccessionNumberList]
  
  given Conversion[Element, DataBank] = (e: Element) =>
    DataBank(
      dataBankName = opElement(e, "DataBankName"),
      accessionNumberList = opElement[AccessionNumberList](e, "AccessionNumberList").get
    )
  
  given Conversion[Element, DataBankList] = (e: Element) =>
    DataBankList(
      completeYN = opAttribute(e, "CompleteYN"),
      dataBank = opArray[DataBank](e, "DataBank").get
    )
  given Conversion[Element, Month] = (e: Element) => Month(value = e.getTextContent)
  
  given Conversion[Element, Day] = (e: Element) => Day(value = e.getTextContent)
  
  given Conversion[Element, DateCompleted] = (e: Element) =>
    DateCompleted(
      year = opElement[Year](e, "Year").get,
      month = opElement[Month](e, "Month").get,
      day = opElement[Day](e, "Day").get
    )
  
  given Conversion[Element, DateRevised] = (e: Element) =>
    DateRevised(
      year = opElement[Year](e, "Year").get,
      month = opElement[Month](e, "Month").get,
      day = opElement[Day](e, "Day").get
    )
  
  given Conversion[Element, SupplMeshName] = (e: Element) =>
    SupplMeshName(
      value = e.getTextContent,
      `type` = opAttribute(e, "Type").get,
      suppMeshName = opAttribute(e, "SuppMeshName"),
      ui = opAttribute(e, "UI").get
    )
  
  given Conversion[Element, SupplMeshList] = (e: Element) =>
    SupplMeshList(supplMeshName = opArray[SupplMeshName](e, "SupplMeshName").get)
  
  given Conversion[Element, CitationSubset] = (e: Element) => CitationSubset(value = e.getTextContent)
  
  given JsonCodec[CitationSubset] = DeriveJsonCodec.gen[CitationSubset]
  
  given Conversion[Element, RefSource] = (e: Element) => RefSource(value = e.getTextContent)
  
  given Conversion[Element, Note] = (e: Element) => Note(value = e.getTextContent)
  
  given Conversion[Element, CommentsCorrections] =
    (e: Element) =>
      CommentsCorrections(
        refType = opAttribute(e, "RefType").get,
        refSource = opElement[RefSource](e, "RefSource").get,
        pmid = opElement(e, "PMID"),
        note = opElement(e, "Note")
      )
  
  given JsonCodec[CommentsCorrections] = DeriveJsonCodec.gen[CommentsCorrections]
  given JsonCodec[RefSource] = DeriveJsonCodec.gen[RefSource]
  given JsonCodec[PMID] = DeriveJsonCodec.gen[PMID]
  given JsonCodec[Note] = DeriveJsonCodec.gen[Note]
  
  
  given Conversion[Element, CommentsCorrectionsList] =
    (e: Element) =>
      CommentsCorrectionsList(
        commentsCorrections = opArray[CommentsCorrections](
          e,
          PubmedTag.CommentsCorrections.tag
        )
      )
  
  given JsonCodec[CommentsCorrectionsList] = DeriveJsonCodec.gen[CommentsCorrectionsList]
  
  given Conversion[Element, GeneSymbol] = (e: Element) =>
    GeneSymbol(
      value = e.getTextContent
    )
  
  given Conversion[Element, GeneSymbolList] = (e: Element) =>
    GeneSymbolList(
      geneSymbol = opArray[GeneSymbol](e, PubmedTag.GeneSymbol.tag)
    )
  
  given Conversion[Element, PersonalNameSubject] =
    (e: Element) =>
      PersonalNameSubject(
        lastName = opElement[LastName](e, PubmedTag.LastName.tag).get,
        foreName = opElement(e, PubmedTag.ForeName.tag),
        initials = opElement(e, PubmedTag.Initials.tag),
        suffix = opElement(e, PubmedTag.Suffix.tag)
      )
  
  given Conversion[Element, PersonalNameSubjectList] =
    (e: Element) =>
      PersonalNameSubjectList(
        personalNameSubject = opArray[PersonalNameSubject](
          e,
          PubmedTag.PersonalNameSubject.tag
        )
      )
  
  given Conversion[Element, OtherID] = (e: Element) =>
    OtherID(
      source = opAttribute(e, "Source").get,
      value = e.getTextContent
    )
  
  given Conversion[Element, OtherAbstract] = (e: Element) =>
    OtherAbstract(
      `type` = opAttribute(e, "Type").get,
      language = opAttribute(e, "Language"),
      abstractText = opArray(e, PubmedTag.AbstractText.tag),
      copyrightInformation = opElement(e, PubmedTag.CopyrightInformation.tag)
    )
  
  given Conversion[Element, NumberOfReferences] = (e: Element) => NumberOfReferences(value = e.getTextContent)
  
  given Conversion[Element, MedlineCitation] = (e: Element) =>
    MedlineCitation(
      status = opAttribute(e, "Status").get,
      versionID = opAttribute(e, "VersionID"),
      versionDate = opAttribute(e, "VersionDate"),
      indexingMethod = opAttribute(e, "IndexingMethod"),
      pmid = opElement[PMID](e, "PMID").get,
      dateCompleted = opElement(e, "DateCompleted"),
      dateRevised = opElement(e, "DateRevised"),
      article = opElement[Article](e, "Article").get,
      medlineJournalInfo = opElement[MedlineJournalInfo](e, "MedlineJournalInfo").get,
      chemicalList = opElement(e, "ChemicalList"),
      supplMeshList = opElement(e, "SupplMeshList"),
      citationSubset = opArray(e, "CitationSubset"),
      commentsCorrectionsList = opElement(e, "CommentsCorrectionsList"),
      geneSymbolList = opElement(e, "GeneSymbolList"),
      meshHeadingList = opElement(e, "MeshHeadingList"),
      numberOfReferences = opElement(e, "NumberOfReferences"),
      personalNameSubjectList = opElement(e, "PersonalNameSubjectList"),
      otherID = opArray(e, "OtherID"),
      otherAbstract = opArray[OtherAbstract](e, PubmedTag.OtherAbstract.tag),
      keywordList = opArray(e, "KeywordList"),
      owner = opAttribute(e, "Owner")
    )
  
  given Conversion[Element, ArticleId] = (e: Element) =>
    ArticleId(
      idType = opAttribute(e, "IdType").get,
      value = e.getTextContent
    )
  
  given JsonCodec[ArticleId] = DeriveJsonCodec.gen[ArticleId]
  
  given Conversion[Element, Citation] = (e: Element) => Citation(value = text(e))
  
  given JsonCodec[Citation] = DeriveJsonCodec.gen[Citation]
  
  given Conversion[Element, ArticleIdList] = (e: Element) => ArticleIdList(articleId = opArray(e, "ArticleId"))
  
  given JsonCodec[ArticleIdList] = DeriveJsonCodec.gen[ArticleIdList]
  
  given Conversion[Element, Reference] = (e: Element) =>
    Reference(
      citation = opElement(e, "Citation"),
      articleIdList = opElement(e, "ArticleIdList")
    )
  
  given Conversion[Element, ReferenceList] = (e: Element) =>
    ReferenceList(
      title = opElement(e, "Title"),
      reference = opArray(e, "Reference"),
      referenceList = opArray(e, "ReferenceList")
    )
  
  given Conversion[Element, PublicationStatus] = (e: Element) => PublicationStatus(value = e.getTextContent)
  
  given Conversion[Element, Hour] = (e: Element) => Hour(value = e.getTextContent)
  
  given Conversion[Element, Minute] = (e: Element) => Minute(value = e.getTextContent)
  
  given Conversion[Element, Second] = (e: Element) => Second(value = e.getTextContent)
  
  given Conversion[Element, PubMedPubDate] = (e: Element) =>
    PubMedPubDate(
      pubStatus = opAttribute(e, "PubStatus").get,
      year = opElement[Year](e, "Year").get,
      month = opElement[Month](e, "Month").get,
      day = opElement[Day](e, "Day").get,
      hour = opElement(e, "Hour"),
      minute = opElement(e, "Minute"),
      second = opElement(e, "Second")
    )
  
  given Conversion[Element, History] = (e: Element) =>
    History(
      pubMedPubDate = opArray[PubMedPubDate](e, "PubMedPubDate").get
    )
  
  given Conversion[Element, Param] = (e: Element) =>
    Param(
      `type` = opAttribute(e, "Type").get,
      value = e.getTextContent
    )
  
  given Conversion[Element, Object] = (e: Element) =>
    Object(
      `type` = opAttribute(e, "Type").get,
      param = opArray(e, "Param")
    )
  
  given Conversion[Element, ObjectList] = (e: Element) =>
    ObjectList(
      `object` = opArray[Object](e, "Object").get
    )
  
  given Conversion[Element, PubmedData] = (e: Element) =>
    PubmedData(
      history = opElement(e, "History"),
      referenceList = opArray(e, "ReferenceList"),
      publicationStatus = opElement[PublicationStatus](e, "PublicationStatus").get,
      articleIdList = opElement[ArticleIdList](e, "ArticleIdList").get,
      objectList = opElement[ObjectList](e, "ObjectList")
    )
  
  given Conversion[Element, PubmedArticle] = (e: Element) =>
    PubmedArticle(
      medlineCitation = opElement[MedlineCitation](e, "MedlineCitation").get,
      pubmedData = opElement(e, "PubmedData")
    )
  
  given Conversion[Element, PublisherName] = (e: Element) =>
    PublisherName(
      value = e.getTextContent
    )
  
  given Conversion[Element, PublisherLocation] = (e: Element) =>
    PublisherLocation(
      value = e.getTextContent
    )
  
  given Conversion[Element, Publisher] = (e: Element) =>
    Publisher(
      publisherName = opElement[PublisherName](e, "PublisherName").get,
      publisherLocation = opElement(e, "PublisherLocation")
    )
  
  given Conversion[Element, BookTitle] = (e: Element) =>
    BookTitle(
      value = text(e)
    )
  
  given JsonCodec[BookTitle] = DeriveJsonCodec.gen[BookTitle]
  
  given Conversion[Element, Season] = (e: Element) => Season(value = e.getTextContent)
  
  given Conversion[Element, EndingDate] = (e: Element) =>
    EndingDate(
      year = opElement[Year](e, "Year").get,
      month = opElement[Month](e, "Month").get,
      day = opElement(e, "Day"),
      season = opElement(e, "Season")
    )
  
  given Conversion[Element, BeginningDate] = (e: Element) =>
    BeginningDate(
      year = opElement[Year](e, "Year").get,
      month = opElement[Month](e, "Month").get,
      day = opElement(e, "Day"),
      season = opElement(e, "Season")
    )
  
  given Conversion[Element, Suffix] = (e: Element) =>
    Suffix(
      value = e.getTextContent
    )
  
  given Conversion[Element, Identifier] = (e: Element) =>
    Identifier(
      source = opAttribute(e, "Source").get,
      value = e.getTextContent
    )
  
  given Conversion[Element, Investigator] = (e: Element) =>
    Investigator(
      lastName = opElement[LastName](e, PubmedTag.LastName.tag).get,
      foreName = opElement(e, PubmedTag.ForeName.tag),
      initials = opElement(e, PubmedTag.Initials.tag),
      suffix = opElement(e, PubmedTag.Suffix.tag),
      identifier = opArray(e, PubmedTag.Identifier.tag),
      affiliationInfo = opArray(e, PubmedTag.AffiliationInfo.tag)
    )
  
  given Conversion[Element, InvestigatorList] = (e: Element) =>
    InvestigatorList(
      investigator = opArray[Investigator](e, PubmedTag.Investigator.tag).get
    )
  
  given Conversion[Element, VolumeTitle] = (e: Element) =>
    VolumeTitle(
      value = e.getTextContent
    )
  
  given Conversion[Element, CollectionTitle] = (e: Element) =>
    CollectionTitle(
      value = text(e)
    )
  
  given JsonCodec[CollectionTitle] = DeriveJsonCodec.gen[CollectionTitle]
  
  given Conversion[Element, Edition] = (e: Element) =>
    Edition(
      value = e.getTextContent
    )
  
  given Conversion[Element, Medium] = (e: Element) =>
    Medium(
      value = e.getTextContent
    )
  
  given Conversion[Element, Isbn] = (e: Element) =>
    Isbn(
      value = e.getTextContent
    )
  
  given Conversion[Element, ReportNumber] = (e: Element) =>
    ReportNumber(
      value = e.getTextContent
    )
  
  given Conversion[Element, Book] = (e: Element) =>
    Book(
      publisher = opElement[Publisher](e, PubmedTag.Publisher.tag).get,
      bookTitle = opElement[BookTitle](e, PubmedTag.BookTitle.tag).get,
      pubDate = opElement[PubDate](e, PubmedTag.PubDate.tag).get,
      beginningDate = opElement(e, PubmedTag.BeginningDate.tag),
      endingDate = opElement(e, PubmedTag.EndingDate.tag),
      authorList = opArray(e, PubmedTag.AuthorList.tag),
      investigatorList = opElement(e, PubmedTag.InvestigatorList.tag),
      volume = opElement(e, PubmedTag.Volume.tag),
      volumeTitle = opElement(e, PubmedTag.VolumeTitle.tag),
      edition = opElement(e, PubmedTag.Edition.tag),
      collectionTitle = opElement(e, PubmedTag.CollectionTitle.tag),
      isbn = opArray(e, PubmedTag.ELocationID.tag),
      elocationID = opArray(e, PubmedTag.ELocationID.tag),
      medium = opElement(e, PubmedTag.Medium.tag),
      reportNumber = opElement(e, PubmedTag.ReportNumber.tag)
    )
  
  given JsonCodec[Book] = DeriveJsonCodec.gen[Book]
  given JsonCodec[Publisher] = DeriveJsonCodec.gen
  given JsonCodec[PublisherName] = DeriveJsonCodec.gen
  given JsonCodec[PublisherLocation] = DeriveJsonCodec.gen
  given JsonCodec[PubDate] = DeriveJsonCodec.gen
  given JsonCodec[MedlineDate] = DeriveJsonCodec.gen
  given JsonCodec[EndingDate] = DeriveJsonCodec.gen
  given JsonCodec[Season] = DeriveJsonCodec.gen
  given JsonCodec[Month] = DeriveJsonCodec.gen
  given JsonCodec[Day] = DeriveJsonCodec.gen
  given JsonCodec[Year] = DeriveJsonCodec.gen
  given JsonCodec[BeginningDate] = DeriveJsonCodec.gen
  given JsonCodec[InvestigatorList] = DeriveJsonCodec.gen
  given JsonCodec[Investigator] = DeriveJsonCodec.gen
  given JsonCodec[ForeName] = DeriveJsonCodec.gen
  given JsonCodec[LastName] = DeriveJsonCodec.gen
  given JsonCodec[Initials] = DeriveJsonCodec.gen
  given JsonCodec[Suffix] = DeriveJsonCodec.gen
  given JsonCodec[Identifier] = DeriveJsonCodec.gen
  given JsonCodec[Volume] = DeriveJsonCodec.gen
  given JsonCodec[VolumeTitle] = DeriveJsonCodec.gen
  given JsonCodec[Edition] = DeriveJsonCodec.gen
  given JsonCodec[Medium] = DeriveJsonCodec.gen
  given JsonCodec[ELocationID] = DeriveJsonCodec.gen
  given JsonCodec[ReportNumber] = DeriveJsonCodec.gen
  given JsonCodec[Isbn] = DeriveJsonCodec.gen
  
  
  given Conversion[Element, LocationLabel] = (e: Element) =>
    LocationLabel(
      `type` = opAttribute(e, "Type"),
      value = e.getTextContent
    )
  
  given Conversion[Element, SectionTitle] = (e: Element) =>
    SectionTitle(
      value = e.getTextContent
    )
  
  given Conversion[Element, Section] = (e: Element) =>
    Section(
      locationLabel = opElement(e, PubmedTag.LocationLabel.tag),
      sectionTitle = opElement[SectionTitle](e, PubmedTag.SectionTitle.tag).get,
      section = opArray(e, PubmedTag.Section.tag)
    )
  
  given Conversion[Element, Item] = (e: Element) =>
    Item(
      value = e.getTextContent
    )
  
  given Conversion[Element, ItemList] = (e: Element) =>
    ItemList(
      item = opArray[Item](e, PubmedTag.Item.tag).get
    )
  
  given Conversion[Element, Sections] = (e: Element) =>
    Sections(
      section = opArray[Section](e, PubmedTag.Section.tag).get
    )
  
  given Conversion[Element, ContributionDate] = (e: Element) =>
    ContributionDate(
      year = opElement[Year](e, PubmedTag.Year.tag).get,
      month = opElement(e, PubmedTag.Month.tag),
      day = opElement(e, PubmedTag.Day.tag),
      season = opElement(e, PubmedTag.Season.tag)
    )
  
  given JsonCodec[ContributionDate] = DeriveJsonCodec.gen[ContributionDate]
  
  given Conversion[Element, BookDocument] = (e: Element) =>
    BookDocument(
      pmid = opElement[PMID](e, PubmedTag.PMID.tag).get,
      articleIdList = opElement[ArticleIdList](e, PubmedTag.ArticleIdList.tag).get,
      book = opElement[Book](e, PubmedTag.Book.tag).get,
      locationLabel = opArray(e, PubmedTag.LocationLabel.tag),
      articleTitle = opElement(e, PubmedTag.ArticleTitle.tag),
      vernacularTitle = opElement(e, PubmedTag.VernacularTitle.tag),
      pagination = opElement(e, PubmedTag.Pagination.tag),
      language = opArray(e, PubmedTag.Language.tag),
      authorList = opArray(e, PubmedTag.AuthorList.tag),
      investigatorList = opElement(e, PubmedTag.InvestigatorList.tag),
      publicationType = opArray(e, PubmedTag.PublicationType.tag),
      `abstract` = opElement(e, PubmedTag.Abstract.tag),
      sections = opElement(e, PubmedTag.Sections.tag),
      keywordList = opArray(e, PubmedTag.KeywordList.tag),
      contributionDate = opElement(e, PubmedTag.ContributionDate.tag),
      dateRevised = opElement(e, PubmedTag.DateRevised.tag),
      grantList = opElement(e, PubmedTag.GrantList.tag),
      itemList = opArray(e, PubmedTag.ItemList.tag),
      referenceList = opArray(e, PubmedTag.ReferenceList.tag)
    )
  
  given JsonCodec[BookDocument] = DeriveJsonCodec.gen
  given JsonCodec[ReferenceList] = DeriveJsonCodec.gen
  given JsonCodec[Title] = DeriveJsonCodec.gen
  given JsonCodec[Reference] = DeriveJsonCodec.gen
  
  given JsonCodec[ArticleTitle] = DeriveJsonCodec.gen
  given JsonCodec[LocationLabel] = DeriveJsonCodec.gen
  given JsonCodec[VernacularTitle] = DeriveJsonCodec.gen
  given JsonCodec[Pagination] = DeriveJsonCodec.gen
  given JsonCodec[MedlinePgn] = DeriveJsonCodec.gen
  given JsonCodec[Language] = DeriveJsonCodec.gen
  given JsonCodec[PublicationType] = DeriveJsonCodec.gen
  given JsonCodec[KeywordList] = DeriveJsonCodec.gen
  given JsonCodec[Keyword] = DeriveJsonCodec.gen
  
  given JsonCodec[Sections] = DeriveJsonCodec.gen
  given JsonCodec[Section] = DeriveJsonCodec.gen
  given JsonCodec[SectionTitle] = DeriveJsonCodec.gen
  given JsonCodec[DateRevised] = DeriveJsonCodec.gen
  given JsonCodec[GrantList] = DeriveJsonCodec.gen
  given JsonCodec[Grant] = DeriveJsonCodec.gen
  given JsonCodec[GrantID] = DeriveJsonCodec.gen
  given JsonCodec[ItemList] = DeriveJsonCodec.gen
  given JsonCodec[Item] = DeriveJsonCodec.gen
  
  
  given Conversion[Element, PubmedBookData] = (e: Element) =>
    PubmedBookData(
      history = opElement(e, PubmedTag.History.tag),
      publicationStatus = opElement[PublicationStatus](
        e,
        PubmedTag.PublicationStatus.tag
      ).get,
      articleIdList = opElement[ArticleIdList](e, PubmedTag.ArticleIdList.tag).get,
      objectList = opElement(e, PubmedTag.ObjectList.tag)
    )
  
  given Conversion[Element, PubmedBookArticle] = (e: Element) =>
    PubmedBookArticle(
      bookDocument = opElement[BookDocument](e, PubmedTag.BookDocument.tag).get,
      pubmedBookData = opElement(e, PubmedTag.PubmedBookData.tag)
    )
  
  given Conversion[Element, DeleteCitation] = (e: Element) =>
    DeleteCitation(
      pmid = opArray[PMID](e, PubmedTag.PMID.tag).get
    )
  
  given Conversion[Element, DeleteDocument] = (e: Element) =>
    DeleteDocument(
      pmid = opArray(e, PubmedTag.PMID.tag)
    )
  
  given Conversion[Element, Journal] = (e: Element) =>
    Journal(
      issn = opElement(e, "ISSN"),
      journalIssue = opElement[JournalIssue](e, "JournalIssue").get,
      title = opElement(e, "Title"),
      abbreviation = opElement(e, "ISOAbbreviation")
    )
  
  given Conversion[Element, PMID] = (el: Element) => PMID(version = el.getAttribute("Version"), value = el.getTextContent)
  
  given Conversion[Element, Title] = (e: Element) => Title(value = e.getTextContent)
  
  given Conversion[Element, ISOAbbreviation] = (e: Element) => ISOAbbreviation(value = e.getTextContent)
  
  given Conversion[Element, ISSN] = (e: Element) => ISSN(issnType = e.getAttribute("IssnType"), value = e.getTextContent)
  
  given Conversion[Element, JournalIssue] = (e: Element) =>
    JournalIssue(
      citedMedium = e.getAttribute("CitedMedium"),
      volume = opElement(e, "Volume"),
      issue = opElement(e, "Issue"),
      pubDate = opElement[PubDate](e, "PubDate").get
    )
  
  given Conversion[Element, Volume] = (e: Element) => Volume(value = e.getTextContent)
  
  given Conversion[Element, Issue] = (e: Element) => Issue(value = e.getTextContent)
  
  given Conversion[Element, PubDate] = (e: Element) =>
    PubDate(
      year = opElement(e, "Year"),
      medlineDate = opElement(e, "MedlineDate")
    )
  
  given Conversion[Element, MedlineDate] = (e: Element) => MedlineDate(value = e.getTextContent)
  
  given Conversion[Element, AbstractText] = (e: Element) =>
    AbstractText(
      label = opAttribute(e, "Label"),
      nlmCategory = opAttribute(e, "NlmCategory"),
      value = text(e)
    )
  
  given JsonCodec[AbstractText] = DeriveJsonCodec.gen[AbstractText]
  
  given Conversion[Element, CopyrightInformation] =
    (e: Element) => CopyrightInformation(value = e.getTextContent)
  
  given JsonCodec[CopyrightInformation] = DeriveJsonCodec.gen[CopyrightInformation]
  
  given Conversion[Element, Abstract] = (e: Element) =>
    Abstract(
      abstractText = opArray(e, "AbstractText"),
      copyrightInformation = opElement(e, "CopyrightInformation")
    )
  
  given JsonCodec[Abstract] = DeriveJsonCodec.gen[Abstract]
  
  given Conversion[Element, MedlinePgn] = (e: Element) => MedlinePgn(value = e.getTextContent)
  
  given Conversion[Element, Pagination] = (e: Element) => Pagination(medlinePgn = opElement(e, "MedlinePgn"))
  
  given Conversion[Element, LastName] = (e: Element) => LastName(value = e.getTextContent)
  
  given Conversion[Element, ForeName] = (e: Element) => ForeName(value = e.getTextContent)
  
  given Conversion[Element, Initials] = (e: Element) => Initials(value = e.getTextContent)
  
  given Conversion[Element, CollectiveName] = (e: Element) => CollectiveName(value = e.getTextContent)
  
  given JsonCodec[CollectiveName] = DeriveJsonCodec.gen[CollectiveName]
  
  given Conversion[Element, Author] = (e: Element) =>
    Author(
      validYN = opAttribute(e, "ValidYN"),
      lastName = opElement(e, "LastName"),
      foreName = opElement(e, "ForeName"),
      initials = opElement(e, "Initials"),
      collectiveName = opElement(e, "CollectiveName"),
      affiliationInfo = opArray(e, "AffiliationInfo")
    )
  
  given JsonCodec[Author] = DeriveJsonCodec.gen[Author]
  
  given Conversion[Element, AffiliationInfo] = (e: Element) => AffiliationInfo(affiliation = opArray(e, "Affiliation"))
  
  given JsonCodec[AffiliationInfo] = DeriveJsonCodec.gen[AffiliationInfo]
  
  given Conversion[Element, Affiliation] = (e: Element) => Affiliation(value = e.getTextContent)
  
  given JsonCodec[Affiliation] = DeriveJsonCodec.gen[Affiliation]
  
  given Conversion[Element, AuthorList] = (e: Element) =>
    AuthorList(
      validYN = opAttribute(e, "ValidYN"),
      author = opArray[Author](e, "Author").getOrElse(List.empty[Author])
    )
  
  given JsonCodec[AuthorList] = DeriveJsonCodec.gen[AuthorList]
  
  given Conversion[Element, Language] = (e: Element) => Language(value = e.getTextContent)
  
  given Conversion[Element, PublicationType] = (e: Element) =>
    PublicationType(
      ui = opAttribute(e, "UI").getOrElse(""),
      value = e.getTextContent
    )
  
  given Conversion[Element, PublicationTypeList] =
    (e: Element) =>
      PublicationTypeList(publicationType =
        opArray[PublicationType](e, "PublicationType").getOrElse(
          List.empty[PublicationType]
        )
      )
  
  given Conversion[Element, ELocationID] = (e: Element) =>
    ELocationID(
      eIdtype = opAttribute(e, "EIdType").getOrElse(""),
      validYN = opAttribute(e, "ValidYN"),
      value = e.getTextContent
    )
  
  given Conversion[Element, GrantID] = (e: Element) => GrantID(value = e.getTextContent)
  
  given Conversion[Element, Acronym] = (e: Element) => Acronym(value = e.getTextContent)
  
  given JsonCodec[Acronym] = DeriveJsonCodec.gen[Acronym]
  
  given Conversion[Element, Agency] = (e: Element) => Agency(value = e.getTextContent)
  
  given JsonCodec[Agency] = DeriveJsonCodec.gen[Agency]
  
  given Conversion[Element, Grant] = (e: Element) =>
    Grant(
      grantID = opElement(e, "GrantID"),
      acronym = opElement(e, "Acronym"),
      agency = opElement(e, "Agency"),
      country = opElement(e, "Country")
    )
  
  given Conversion[Element, GrantList] = (e: Element) =>
    GrantList(
      completeYN = opAttribute(e, "CompleteYN"),
      grant = opArray[Grant](e, "Grant").getOrElse(List.empty[Grant])
    )
  
  given Conversion[Element, VernacularTitle] = (e: Element) => VernacularTitle(value = text(e))
  
  given Conversion[Element, ArticleDate] = (e: Element) =>
    ArticleDate(
      year = opElement[Year](e, "Year").get,
      month = opElement[Month](e, "Month").get,
      day = opElement[Day](e, "Day").get,
      dateType = opAttribute(e, "DateType")
    )
  
  given JsonCodec[ArticleDate] = DeriveJsonCodec.gen[ArticleDate]
  
  given Conversion[Element, Article] = (e: Element) =>
    Article(
      pubModel = opAttribute(e, "PubModel").getOrElse(""),
      journal = opElement[Journal](e, "Journal").get,
      articleTitle = opElement(e, "ArticleTitle"),
      pagination = opElement(e, "Pagination"),
      elocationID = opArray[ELocationID](e, "ELocationID"),
      `abstract` = opElement(e, "Abstract"),
      authorList = opElement(e, "AuthorList"),
      language = opArray[Language](e, "Language").getOrElse(List.empty[Language]),
      dataBankList = opElement(e, "DataBankList"),
      grantList = opElement(e, "GrantList"),
      publicationTypeList = opElement(e, "PublicationTypeList"),
      vernacularTitle = opElement(e, "VernacularTitle"),
      articleDate = opArray(e, "ArticleDate")
    )
  
  given Conversion[Element, ArticleTitle] = (e: Element) => ArticleTitle(value = text(e))
  
  given Conversion[Element, Country] = (e: Element) => Country(value = e.getTextContent)
  
  given JsonCodec[Country] = DeriveJsonCodec.gen[Country]
  
  given Conversion[Element, MedlineTA] = (e: Element) => MedlineTA(value = e.getTextContent)
  
  given JsonCodec[MedlineTA] = DeriveJsonCodec.gen[MedlineTA]
  
  given Conversion[Element, NlmUniqueID] = (e: Element) => NlmUniqueID(value = e.getTextContent)
  
  given JsonCodec[NlmUniqueID] = DeriveJsonCodec.gen[NlmUniqueID]
  
  given Conversion[Element, ISSNLinking] = (e: Element) => ISSNLinking(value = e.getTextContent)
  
  given JsonCodec[ISSNLinking] = DeriveJsonCodec.gen[ISSNLinking]
  
  given Conversion[Element, MedlineJournalInfo] = (e: Element) =>
    MedlineJournalInfo(
      country = opElement(e, "Country"),
      medlineTA = opElement(e, "MedlineTA"),
      nlmUniqueID = opElement[NlmUniqueID](e, "NlmUniqueID").get,
      issnLinking = opElement(e, "ISSNLinking")
    )
  
  given JsonCodec[MedlineJournalInfo] = DeriveJsonCodec.gen[MedlineJournalInfo]
  
  given Conversion[Element, DescriptorName] = (e: Element) =>
    DescriptorName(
      ui = opAttribute(e, "UI").get,
      majorTopicYN = opAttribute(e, "MajorTopicYN"),
      `type` = opAttribute(e, "Type"),
      value = e.getTextContent
    )
  
  given Conversion[Element, QualifierName] = (e: Element) =>
    QualifierName(
      ui = opAttribute(e, "UI").get,
      majorTopicYN = opAttribute(e, "MajorTopicYN"),
      value = e.getTextContent
    )
  
  given Conversion[Element, MeshHeading] = (e: Element) =>
    MeshHeading(
      descriptorName = opElement[DescriptorName](e, "DescriptorName").get,
      qualifierName = opArray[QualifierName](e, "QualifierName")
    )
  
  given Conversion[Element, MeshHeadingList] = (e: Element) =>
    MeshHeadingList(meshHeading =
      opArray[MeshHeading](e, "MeshHeading").getOrElse(
        List.empty[MeshHeading]
      )
    )
  
  given Conversion[Element, RegistryNumber] = (e: Element) => RegistryNumber(value = e.getTextContent)
  
  given Conversion[Element, NameOfSubstance] = (e: Element) =>
    NameOfSubstance(ui = opAttribute(e, "UI").get, value = e.getTextContent)
  
  given Conversion[Element, Chemical] = (e: Element) =>
    Chemical(
      registryNumber = opElement[RegistryNumber](e, "RegistryNumber").get,
      nameOfSubstance = opElement[NameOfSubstance](e, "NameOfSubstance").get
    )
  
  given JsonCodec[Chemical] = DeriveJsonCodec.gen
  given JsonCodec[RegistryNumber] = DeriveJsonCodec.gen
  given JsonCodec[NameOfSubstance] = DeriveJsonCodec.gen
  
  given Conversion[Element, ChemicalList] = (e: Element) => ChemicalList(chemical = opArray[Chemical](e, "Chemical").get)
  
  given JsonCodec[ChemicalList] = DeriveJsonCodec.gen
  
  given Conversion[Element, Year] = (e: Element) => Year(value = e.getTextContent)
  
  given JsonCodec[PubmedArticle] = DeriveJsonCodec.gen
  given JsonCodec[PubmedData] = DeriveJsonCodec.gen
  given JsonCodec[ObjectList] = DeriveJsonCodec.gen
  given JsonCodec[Object] = DeriveJsonCodec.gen
  given JsonCodec[Param] = DeriveJsonCodec.gen
  given JsonCodec[History] = DeriveJsonCodec.gen
  given JsonCodec[PubMedPubDate] = DeriveJsonCodec.gen
  given JsonCodec[Hour] = DeriveJsonCodec.gen
  given JsonCodec[Minute] = DeriveJsonCodec.gen
  given JsonCodec[Second] = DeriveJsonCodec.gen
  given JsonCodec[PublicationStatus] = DeriveJsonCodec.gen
  given JsonCodec[MedlineCitation] = DeriveJsonCodec.gen
  given JsonCodec[DateCompleted] = DeriveJsonCodec.gen
  given JsonCodec[Article] = DeriveJsonCodec.gen
  given JsonCodec[Journal] = DeriveJsonCodec.gen
  given JsonCodec[ISSN] = DeriveJsonCodec.gen
  given JsonCodec[JournalIssue] = DeriveJsonCodec.gen
  given JsonCodec[Issue] = DeriveJsonCodec.gen
  given JsonCodec[ISOAbbreviation] = DeriveJsonCodec.gen
  given JsonCodec[DataBankList] = DeriveJsonCodec.gen
  given JsonCodec[DataBank] = DeriveJsonCodec.gen
  given JsonCodec[DataBankName] = DeriveJsonCodec.gen
  given JsonCodec[PublicationTypeList] = DeriveJsonCodec.gen
  
  given JsonCodec[SupplMeshList] = DeriveJsonCodec.gen
  given JsonCodec[SupplMeshName] = DeriveJsonCodec.gen
  given JsonCodec[GeneSymbolList] = DeriveJsonCodec.gen
  given JsonCodec[GeneSymbol] = DeriveJsonCodec.gen
  given JsonCodec[MeshHeadingList] = DeriveJsonCodec.gen
  given JsonCodec[MeshHeading] = DeriveJsonCodec.gen
  given JsonCodec[DescriptorName] = DeriveJsonCodec.gen
  given JsonCodec[QualifierName] = DeriveJsonCodec.gen
  given JsonCodec[NumberOfReferences] = DeriveJsonCodec.gen
  given JsonCodec[PersonalNameSubjectList] = DeriveJsonCodec.gen
  given JsonCodec[PersonalNameSubject] = DeriveJsonCodec.gen
  given JsonCodec[OtherID] = DeriveJsonCodec.gen
  given JsonCodec[OtherAbstract] = DeriveJsonCodec.gen
  given JsonCodec[PubmedBookArticle] = DeriveJsonCodec.gen
  given JsonCodec[PubmedBookData] = DeriveJsonCodec.gen
  
  given JsonCodec[DeleteCitation] = DeriveJsonCodec.gen
  given JsonCodec[DeleteDocument] = DeriveJsonCodec.gen
  
  
  private def opElement[T](e: Element, tag: String)(using Conversion[Element, T]): Option[T] =
    val elements = e.getElementsByTagName(tag)
    if (elements.getLength == 0) {
      None
    } else
      Some(elements.item(0).asInstanceOf[Element])
  
  private def opArray[T: ClassTag](e: Element, tag: String)(using Conversion[Element, T]): Option[List[T]] =
    val elements = e.getElementsByTagName(tag)
  
    if (elements.getLength == 0) {
      None
    } else {
      val builder: mutable.ArrayBuffer[T] = new mutable.ArrayBuffer[T]
      for (i <- 0 until elements.getLength) {
        val nodeType = elements.item(i).getNodeType
        if (nodeType == Node.ELEMENT_NODE) {
          val element = elements.item(i).asInstanceOf[Element]
          builder.addOne(element)
        }
      }
      val arr: Array[T] = builder.toArray[T]
      Some(arr.toList)
    }
  private def opAttribute(e: Element, name: String): Option[String] =
    val attr: String = e.getAttribute(name)
    if (attr == "") {
      None
    } else
      Some(attr)
  
  private def text(e: Element): String =
    val children = e.getChildNodes
    if (children.getLength == 0) {
      ""
    } else {
      val builder = new StringBuilder
      for (i <- 0 until children.getLength) {
        val node = children.item(i)
        val nodeType = node.getNodeType
        nodeType match
          case Node.ELEMENT_NODE =>
            val name = node.asInstanceOf[Element].getTagName
            val t = node.getTextContent
            val s: String = xml.Utility.escape(s"""<{$name}>{$t}</{$name}>""")
            builder.addAll(s)
          case Node.TEXT_NODE =>
            builder.addAll(node.getTextContent)
          case _ =>
      }
      builder.result()
    }
  