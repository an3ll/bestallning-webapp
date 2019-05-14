package se.an3ll.bestallning.app.routes

import se.an3ll.bestallning.app.domain.BestallningStatus

data class ListBestallningarQuery(
  val statusar: List<BestallningStatus>,
  val hsaId: String,
  val orgNrVardgivare: String,
  val textSearch: String?,
  val pageIndex: Int,
  val limit: Int,
  val sortColumn: ListBestallningSortColumn,
  val sortDirection: ListBestallningDirection)

enum class ListBestallningSortColumn(val kolumn: String) {
  ID("id"),
  INTYG_TYP("intygTyp"),
  ANKOMST_DATUM("ankomstDatum"),
  STATUS("status"),
  INVANARE_PERSON_ID("invanare.personId")
}

enum class ListBestallningDirection {
  DESC,
  ASC
}
