package se.an3ll.bestallning.app.converters

import io.ktor.config.MapApplicationConfig
import io.ktor.server.testing.withTestApplication
import org.jetbrains.exposed.sql.transactions.transaction
import org.spekframework.spek2.Spek
import se.an3ll.bestallning.app.config.BootstrapData
import se.an3ll.bestallning.app.domain.BestallningStatus
import se.an3ll.bestallning.app.module
import kotlin.test.assertEquals

object ConvertersTest : Spek({

  withTestApplication({
    (environment.config as MapApplicationConfig).apply {
      put("ktor.environment", "dev")
      put("ktor.deployment.port", "8080")
      put("ktor.application.modules", listOf("se.an3ll.bestallning.app.ApplicationKt.module"))
    }
    module()
  }) {

    group("Converter Tests") {

      test("BestallningEntity -> Bestallning") {

        transaction {
          val persistedEntity = BootstrapData.bootstrapBestallningar()[0]

          val domain = persistedEntity.toDomain()

          assertEquals(persistedEntity.typ, domain.typ)
          assertEquals(persistedEntity.intygTyp, domain.intygTyp)
          assertEquals(persistedEntity.intygTypBeskrivning, domain.intygTypBeskrivning)
          assertEquals(persistedEntity.ankomstDatum.toString(), domain.ankomstDatum)
          assertEquals(persistedEntity.avslutDatum?.toString(), domain.avslutDatum)
          assertEquals(persistedEntity.syfte, domain.syfte)
          assertEquals(persistedEntity.arendeReferens, domain.arendeReferens)
          assertEquals(persistedEntity.planeradeAktiviteter, domain.planeradeAktiviteter)
          assertEquals(BestallningStatus.valueOf(persistedEntity.status), domain.status)
          assertEquals(persistedEntity.statusString, domain.statusString)
          assertEquals(persistedEntity.invanarePersonId, domain.invanarePersonId)
          assertEquals(persistedEntity.invanareBakgrundNulage, domain.invanareBakgrundNulage)
          assertEquals(persistedEntity.vardenhetHsaId, domain.vardenhetHsaId)
          assertEquals(persistedEntity.vardenhetVardgivareHsaId, domain.vardenhetVardgivareHsaId)
          assertEquals(persistedEntity.vardenhetVardgivareOrganisationId, domain.vardenhetVardgivareOrganisationId)
          assertEquals(persistedEntity.vardenhetNamn, domain.vardenhetNamn)
          assertEquals(persistedEntity.vardenhetEpost, domain.vardenhetEpost)

          assertEquals(persistedEntity.handlaggare.fullstandigtNamn, domain.handlaggare.fullstandigtNamn)
          assertEquals(persistedEntity.handlaggare.telefonnummer, domain.handlaggare.telefonnummer)
          assertEquals(persistedEntity.handlaggare.myndighet, domain.handlaggare.myndighet)
          assertEquals(persistedEntity.handlaggare.kontor, domain.handlaggare.kontor)
          assertEquals(persistedEntity.handlaggare.adress, domain.handlaggare.adress)
          assertEquals(persistedEntity.handlaggare.postnummer, domain.handlaggare.postnummer)
          assertEquals(persistedEntity.handlaggare.stad, domain.handlaggare.stad)
          assertEquals(persistedEntity.handlaggare.kostnadsstalle, domain.handlaggare.kostnadsstalle)

        }
      }
    }
  }
})
