package se.an3ll.bestallning.app.persistence

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

object BestallningarTable : IntIdTable("bestallning") {
  val typ = varchar("typ", 255)
  val intygTyp = varchar("intyg_typ", 255)
  val intygTypBeskrivning = varchar("intyg_typ_beskrivning", 255)
  val ankomstDatum = datetime("ankomst_datum")
  val avslutDatum = datetime("avslut_datum").nullable()
  val syfte = varchar("syfte", 255).nullable()
  val arendeReferens = varchar("arende_referens", 255).nullable()
  val planeradeAktiviteter = varchar("planerade_aktiviteter", 255).nullable()
  val status = varchar("status", 255)
  val statusString = varchar("status_string", 255)
  val invanarePersonId = varchar("invanare_person_id", 255)
  val invanareBakgrundNulage = varchar("invanare_bakgrund_nulage", 255).nullable()
  val handlaggare = reference("handlaggare_id", HandlaggareTable)
  val vardenhetHsaId = varchar("vardenhet_hsa_id", 255)
  val vardenhetVardgivareHsaId = varchar("vardenhet_vardgivare_hsa_id", 255)
  val vardenhetVardgivareOrganisationId = varchar("vardenhet_vardgivare_organisation_id", 255)
  val vardenhetNamn = varchar("vardenhet_namn", 255)
  val vardenhetEpost = varchar("vardenhet_epost", 255)

}

class BestallningEntity(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<BestallningEntity>(BestallningarTable)

  var typ by BestallningarTable.typ
  var intygTyp by BestallningarTable.intygTyp
  var intygTypBeskrivning by BestallningarTable.intygTypBeskrivning
  var ankomstDatum by BestallningarTable.ankomstDatum
  var avslutDatum by BestallningarTable.avslutDatum
  var syfte by BestallningarTable.syfte
  var arendeReferens by BestallningarTable.arendeReferens
  var planeradeAktiviteter by BestallningarTable.planeradeAktiviteter
  var status by BestallningarTable.status
  var statusString by BestallningarTable.statusString
  var invanarePersonId by BestallningarTable.invanarePersonId
  var invanareBakgrundNulage by BestallningarTable.invanareBakgrundNulage
  var handlaggare by HandlaggareEntity referencedOn BestallningarTable.handlaggare
  var vardenhetHsaId by BestallningarTable.vardenhetHsaId
  var vardenhetVardgivareHsaId by BestallningarTable.vardenhetVardgivareHsaId
  var vardenhetVardgivareOrganisationId by BestallningarTable.vardenhetVardgivareOrganisationId
  var vardenhetNamn by BestallningarTable.vardenhetNamn
  var vardenhetEpost by BestallningarTable.vardenhetEpost
}

object HandlaggareTable : IntIdTable("handlaggare") {
  val fullstandigtNamn = varchar("fullstandigt_namn", 255).nullable()
  val telefonnummer = varchar("telefonnummer", 255).nullable()
  val myndighet = varchar("myndighet", 255)
  val kontor = varchar("kontor", 255).nullable()
  val adress = varchar("adress", 255).nullable()
  val postnummer = varchar("postnummer", 255).nullable()
  val stad = varchar("stad", 255).nullable()
  val kostnadsstalle = varchar("kostnadsstalle", 255).nullable()
}

class HandlaggareEntity(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<HandlaggareEntity>(HandlaggareTable)

  var fullstandigtNamn by HandlaggareTable.fullstandigtNamn
  var telefonnummer by HandlaggareTable.telefonnummer
  var myndighet by HandlaggareTable.myndighet
  var kontor by HandlaggareTable.kontor
  var adress by HandlaggareTable.adress
  var postnummer by HandlaggareTable.postnummer
  var stad by HandlaggareTable.stad
  var kostnadsstalle by HandlaggareTable.kostnadsstalle
}

