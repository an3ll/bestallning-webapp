package se.an3ll.bestallning.app.services

import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.or
import org.jetbrains.exposed.sql.transactions.transaction
import se.an3ll.bestallning.app.converters.toDomain
import se.an3ll.bestallning.app.domain.Bestallning
import se.an3ll.bestallning.app.persistence.BestallningEntity
import se.an3ll.bestallning.app.persistence.BestallningarTable
import se.an3ll.bestallning.app.routes.ListBestallningarQuery

interface BestallningService {
  fun listByQuery(query: ListBestallningarQuery): List<Bestallning>
}

class BestallningServiceImpl : BestallningService {

  override fun listByQuery(query: ListBestallningarQuery): List<Bestallning> {
    return transaction {
      query.textSearch?.let {
        val op: Op<Boolean> =
          (BestallningarTable.statusString like "%$it%") or
          (BestallningarTable.intygTyp like "%$it%") or
          (BestallningarTable.invanarePersonId like "%$it%") or
          (BestallningarTable.ankomstDatumString like "%$it%")

        BestallningEntity.find(op)
          .limit(query.limit)
          .sortedBy(BestallningEntity::id)
      }
        ?.map { entity -> entity.toDomain() }
        .orEmpty()
    }
  }
}
