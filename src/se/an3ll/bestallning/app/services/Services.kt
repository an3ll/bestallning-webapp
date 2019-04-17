package se.an3ll.bestallning.app.services

import org.jetbrains.exposed.sql.transactions.transaction
import se.an3ll.bestallning.app.domain.Bestallning
import se.an3ll.bestallning.app.persistence.BestallningEntity
import se.an3ll.bestallning.app.persistence.toDomain

interface BestallningService {
    fun listBestallningar(): List<Bestallning>
}

class BestallningServiceImpl : BestallningService {
    override fun listBestallningar(): List<Bestallning> {
        return transaction {
            BestallningEntity.all().map { it.toDomain() }
        }
    }
}
