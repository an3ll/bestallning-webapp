package se.an3ll.bestallning.app.converters

import se.an3ll.bestallning.app.domain.Bestallning
import se.an3ll.bestallning.app.domain.BestallningStatus
import se.an3ll.bestallning.app.domain.Handlaggare
import se.an3ll.bestallning.app.persistence.BestallningEntity
import se.an3ll.bestallning.app.persistence.HandlaggareEntity

fun BestallningEntity.toDomain(): Bestallning {
  return Bestallning(
    typ = this.typ,
    intygTyp = this.intygTyp,
    intygTypBeskrivning = this.intygTypBeskrivning,
    ankomstDatum = this.ankomstDatum,
    avslutDatum = this.avslutDatum,
    syfte = this.syfte,
    arendeReferens = this.arendeReferens,
    planeradeAktiviteter = this.planeradeAktiviteter,
    status = BestallningStatus.valueOf(this.status),
    statusString = this.statusString,
    invanarePersonId = this.invanarePersonId,
    invanareBakgrundNulage = this.invanareBakgrundNulage,
    handlaggare = this.handlaggare.toDomain(),
    vardenhetHsaId = this.vardenhetHsaId,
    vardenhetVardgivareHsaId = this.vardenhetVardgivareHsaId,
    vardenhetVardgivareOrganisationId = this.vardenhetVardgivareOrganisationId,
    vardenhetNamn = this.vardenhetNamn,
    vardenhetEpost = this.vardenhetEpost
  )
}

fun HandlaggareEntity.toDomain(): Handlaggare {
  return Handlaggare(
    fullstandigtNamn = this.fullstandigtNamn,
    telefonnummer = this.telefonnummer,
    myndighet = this.myndighet,
    kontor = this.kontor,
    adress = this.adress,
    postnummer = this.postnummer,
    stad = this.stad,
    kostnadsstalle = this.kostnadsstalle
  )
}
