package se.an3ll.bestallning.app.persistence

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable

private const val VARCHAR_255 = 255

object BestallningarTable : IntIdTable("bestallning") {
  val typ = varchar("typ", VARCHAR_255)
  val intygTyp = varchar("intyg_typ", VARCHAR_255)
  val intygTypBeskrivning = varchar("intyg_typ_beskrivning", VARCHAR_255)
  val ankomstDatum = datetime("ankomst_datum")
  val ankomstDatumString = varchar("ankomst_datum_string", VARCHAR_255)
  val avslutDatum = datetime("avslut_datum").nullable()
  val syfte = varchar("syfte", VARCHAR_255).nullable()
  val arendeReferens = varchar("arende_referens", VARCHAR_255).nullable()
  val planeradeAktiviteter = varchar("planerade_aktiviteter", VARCHAR_255).nullable()
  val status = varchar("status", VARCHAR_255)
  val statusString = varchar("status_string", VARCHAR_255)
  val invanarePersonId = varchar("invanare_person_id", VARCHAR_255)
  val invanareBakgrundNulage = varchar("invanare_bakgrund_nulage", VARCHAR_255).nullable()
  val handlaggare = reference("handlaggare_id", HandlaggareTable)
  val vardenhetHsaId = varchar("vardenhet_hsa_id", VARCHAR_255)
  val vardenhetVardgivareHsaId = varchar("vardenhet_vardgivare_hsa_id", VARCHAR_255)
  val vardenhetVardgivareOrganisationId = varchar("vardenhet_vardgivare_organisation_id", VARCHAR_255)
  val vardenhetNamn = varchar("vardenhet_namn", VARCHAR_255)
  val vardenhetEpost = varchar("vardenhet_epost", VARCHAR_255)

}

class BestallningEntity(id: EntityID<Int>) : IntEntity(id) {
  companion object : IntEntityClass<BestallningEntity>(BestallningarTable)

  var typ by BestallningarTable.typ
  var intygTyp by BestallningarTable.intygTyp
  var intygTypBeskrivning by BestallningarTable.intygTypBeskrivning
  var ankomstDatum by BestallningarTable.ankomstDatum
  var ankomstDatumString by BestallningarTable.ankomstDatumString
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
  val fullstandigtNamn = varchar("fullstandigt_namn", VARCHAR_255).nullable()
  val telefonnummer = varchar("telefonnummer", VARCHAR_255).nullable()
  val myndighet = varchar("myndighet", VARCHAR_255)
  val kontor = varchar("kontor", VARCHAR_255).nullable()
  val adress = varchar("adress", VARCHAR_255).nullable()
  val postnummer = varchar("postnummer", VARCHAR_255).nullable()
  val stad = varchar("stad", VARCHAR_255).nullable()
  val kostnadsstalle = varchar("kostnadsstalle", VARCHAR_255).nullable()
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

