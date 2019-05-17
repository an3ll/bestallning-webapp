package se.an3ll.bestallning.app.config

import org.joda.time.DateTime
import se.an3ll.bestallning.app.persistence.BestallningEntity
import se.an3ll.bestallning.app.persistence.HandlaggareEntity

@Suppress("LongMethod")
object BootstrapData {

  fun bootstrapBestallningar(): List<BestallningEntity> {

    val handlaggare1: HandlaggareEntity = HandlaggareEntity.new {
      fullstandigtNamn = "HandlaggareNamn"
      telefonnummer = "0733707070"
      myndighet = "AF"
      kontor = "Kontoret"
      adress = "Adressvägen 11"
      postnummer = "12345"
      stad = "Stockholm"
      kostnadsstalle = "Stället"
    }

    val handlaggare2: HandlaggareEntity = HandlaggareEntity.new {
      fullstandigtNamn = "HandlaggareNamn2"
      telefonnummer = "0733707070"
      myndighet = "AF"
      kontor = "Kontoret2"
      adress = "Adressvägen 12"
      postnummer = "23456"
      stad = "Stockholm"
      kostnadsstalle = "Stället2"
    }

    var now = DateTime.now()
    var nowString = now.toString()

    val bestallning1 = BestallningEntity.new {
      typ = "F1.0_AF00213"
      intygTyp = "AF00213"
      intygTypBeskrivning = "Arbetsförmedlingens medicinska utlåtande"
      ankomstDatum = now
      ankomstDatumString = nowString
      avslutDatum = null
      syfte = "viktigt syfte"
      arendeReferens = "referens1"
      planeradeAktiviteter = "aktiviteter"
      status = "OLAST"
      statusString = "Oläst"
      invanarePersonId = "19121212-1212"
      invanareBakgrundNulage = "bakgrund"
      handlaggare = handlaggare1
      vardenhetHsaId = "hsaId"
      vardenhetVardgivareHsaId = "org-hsa-id"
      vardenhetVardgivareOrganisationId = "org-id"
      vardenhetNamn = "Namnet"
      vardenhetEpost = "e@post"
    }

    now = DateTime.now()
    nowString = now.toString()

    val bestallning2 = BestallningEntity.new {
      typ = "F1.0_AF00213"
      intygTyp = "AF00213"
      intygTypBeskrivning = "Arbetsförmedlingens medicinska utlåtande"
      ankomstDatum = now
      ankomstDatumString = nowString
      avslutDatum = null
      syfte = "viktigt syfte"
      arendeReferens = "referens1"
      planeradeAktiviteter = "aktiviteter"
      status = "OLAST"
      statusString = "Oläst"
      invanarePersonId = "19121212-1212"
      invanareBakgrundNulage = "bakgrund"
      handlaggare = handlaggare2
      vardenhetHsaId = "hsaId"
      vardenhetVardgivareHsaId = "org-hsa-id"
      vardenhetVardgivareOrganisationId = "org-id"
      vardenhetNamn = "Namnet"
      vardenhetEpost = "e@post"
    }

    return listOf(bestallning1, bestallning2)

  }
}
