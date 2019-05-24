package se.an3ll.bestallning.app.domain

import org.joda.time.DateTime

data class Bestallning(
  val typ: String,
  val intygTyp: String,
  val intygTypBeskrivning: String,
  val ankomstDatum: DateTime,
  val avslutDatum: DateTime?,
  val syfte: String?,
  val arendeReferens: String?,
  val planeradeAktiviteter: String?,
  val status: BestallningStatus,
  val statusString: String,
  val invanarePersonId: String,
  val invanareBakgrundNulage: String?,
  val handlaggare: Handlaggare,
  val vardenhetHsaId: String,
  val vardenhetVardgivareHsaId: String,
  val vardenhetVardgivareOrganisationId: String,
  val vardenhetNamn: String,
  val vardenhetEpost: String
)

enum class BestallningStatus(val beskrivning: String) {
  UNDEFINED("undefined"),
  OLAST("Oläst"),
  LAST("Läst"),
  AVVISAD("Avvisad"),
  RADERAD("Raderad"),
  ACCEPTERAD("Accepterad"),
  KLAR("Klar")
}

data class Handlaggare(
  var fullstandigtNamn: String? = null,
  var telefonnummer: String? = null,
  val myndighet: String,
  var kontor: String? = null,
  var adress: String? = null,
  var postnummer: String? = null,
  var stad: String? = null,
  var kostnadsstalle: String? = null
)
