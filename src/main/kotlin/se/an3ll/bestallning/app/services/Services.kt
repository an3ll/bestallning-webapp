package se.an3ll.bestallning.app.services

import org.jetbrains.exposed.sql.transactions.transaction
import se.an3ll.bestallning.app.domain.Bestallning
import se.an3ll.bestallning.app.routes.ListBestallningarQuery

interface BestallningService {
  fun listByQuery(query: ListBestallningarQuery): List<Bestallning>
}

class BestallningServiceImpl(val bestallningPersistenceService: BestallningPersistenceService) : BestallningService {

  override fun listByQuery(query: ListBestallningarQuery): List<Bestallning> {
    return transaction {
      bestallningPersistenceService.listByQuery(query)
    }
  }
}
