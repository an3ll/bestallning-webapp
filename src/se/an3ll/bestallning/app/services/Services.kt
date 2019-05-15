package se.an3ll.bestallning.app.services

import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import se.an3ll.bestallning.app.converters.toDomain
import se.an3ll.bestallning.app.domain.Bestallning
import se.an3ll.bestallning.app.persistence.BestallningEntity
import se.an3ll.bestallning.app.persistence.BestallningarTable
import se.an3ll.bestallning.app.routes.ListBestallningarQuery

interface BestallningService {
  fun listBestallningar(): List<Bestallning>
  fun listByQuery(query: ListBestallningarQuery): List<Bestallning>
}

class BestallningServiceImpl : BestallningService {

  override fun listBestallningar(): List<Bestallning> {
    return transaction {
      BestallningEntity.all().map { bestallningEntity -> bestallningEntity.toDomain() }
    }
  }

  override fun listByQuery(query: ListBestallningarQuery): List<Bestallning> {
    return transaction {
      query.textSearch?.let {

        BestallningEntity.find {
          (BestallningarTable.statusString like "%$it%") or
              (BestallningarTable.invanarePersonId like "%$it%")
        }
          .limit(query.limit)
          .sortedBy { it.id }
      }
        ?.map { entity -> entity.toDomain() }
        .orEmpty()
    }
  }
}
