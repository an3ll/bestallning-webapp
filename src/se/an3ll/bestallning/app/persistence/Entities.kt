package se.an3ll.bestallning.app.persistence

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.IntIdTable
import se.an3ll.bestallning.app.domain.Bestallning

object BestallningarEntity : IntIdTable("bestallning") {
    val name = varchar("name", 255)
    val type = varchar("type", 255)
    val personnummer = varchar("personnummer", 255)
}

class BestallningEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<BestallningEntity>(BestallningarEntity)

    var name by BestallningarEntity.name
    var type by BestallningarEntity.type
    var personnummer by BestallningarEntity.personnummer
}

fun BestallningEntity.toDomain(): Bestallning {
    return Bestallning(
        id = this.id.value,
        name = this.name,
        type = this.type,
        personnummer = this.personnummer
    )
}
