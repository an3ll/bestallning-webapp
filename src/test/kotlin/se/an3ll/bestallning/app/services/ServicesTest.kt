package se.an3ll.bestallning.app.services

import io.mockk.every
import io.mockk.mockk
import org.joda.time.DateTime
import org.spekframework.spek2.Spek
import se.an3ll.bestallning.app.domain.Bestallning
import se.an3ll.bestallning.app.domain.BestallningStatus
import se.an3ll.bestallning.app.domain.Handlaggare
import se.an3ll.bestallning.app.routes.ListBestallningDirection
import se.an3ll.bestallning.app.routes.ListBestallningSortColumn
import se.an3ll.bestallning.app.routes.ListBestallningarQuery
import se.an3ll.bestallning.app.services.TestData.bestallningList
import se.an3ll.bestallning.app.services.TestData.bestallningQuery
import kotlin.test.assertEquals

object ServicesTest : Spek({

  group("BestallningServiceImpl Tests") {

    test("test listByQuery method") {

      val bestallningPersistenceService = mockk<BestallningPersistenceService>()

      every { bestallningPersistenceService.listByQuery(eq(bestallningQuery)) } returns bestallningList

      val actualResult = bestallningPersistenceService.listByQuery(bestallningQuery)
      assertEquals(bestallningList, actualResult)
    }
  }
})

object TestData {
  val bestallningList = listOf(
    Bestallning(
      typ = "F1.0_AF00213",
      intygTyp = "AF00213",
      intygTypBeskrivning = "Arbetsförmedlingens medicinska utlåtande",
      ankomstDatum = DateTime.now().toString(),
      avslutDatum = null,
      syfte = "viktigt syfte",
      arendeReferens = "referens1",
      planeradeAktiviteter = "aktiviteter",
      status = BestallningStatus.OLAST,
      statusString = "Oläst",
      invanarePersonId = "19121212-1212",
      invanareBakgrundNulage = "bakgrund",
      handlaggare = Handlaggare(myndighet = "FK"),
      vardenhetHsaId = "hsaId",
      vardenhetVardgivareHsaId = "org-hsa-id",
      vardenhetVardgivareOrganisationId = "org-id",
      vardenhetNamn = "Namnet",
      vardenhetEpost = "e@post"
    )
  )

  val bestallningQuery = ListBestallningarQuery(
    statusar = listOf(BestallningStatus.OLAST),
    hsaId = "hsaId",
    orgNrVardgivare = "OrgNr",
    textSearch = "olä",
    pageIndex = 0,
    limit = 50,
    sortColumn = ListBestallningSortColumn.ANKOMST_DATUM,
    sortDirection = ListBestallningDirection.ASC
  )
}
