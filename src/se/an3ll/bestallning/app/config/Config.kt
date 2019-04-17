package se.an3ll.bestallning.app.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.FluentConfiguration
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import se.an3ll.bestallning.app.persistence.BestallningEntity
import se.an3ll.bestallning.app.routes.bestallningRoutes
import se.an3ll.bestallning.app.routes.userRoutes
import se.an3ll.bestallning.app.services.BestallningService
import se.an3ll.bestallning.app.services.BestallningServiceImpl

fun Application.config() {
    features()
    initDb()
    bestallningRoutes()
    userRoutes()
}

fun Application.features() {

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Koin) {
        modules(applicationModule)
    }
}


val applicationModule = module {
    single<BestallningService> { BestallningServiceImpl() }
}

fun dataSource(): HikariDataSource {
    val config = HikariConfig()
    config.schema = "public"
    config.driverClassName = "org.postgresql.Driver"
    config.jdbcUrl = "jdbc:postgresql:joakimanell"
    config.maximumPoolSize = 3
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    config.username = "joakimanell"
    config.password = "password"
    config.validate()
    return HikariDataSource(config)
}

fun initDb() {

    val dataSource = dataSource()

    val profile = ConfigFactory.load().getString("profile")

    if (profile == "dev") {
        DBMigration.clean(dataSource)
    }

    DBMigration.migrate(dataSource)
    Database.connect(dataSource)

    //bootstrap data
    transaction {
        BestallningEntity.new {
            name = "testNamn1"
            type = "AF32001"
            personnummer = "19121212-1212"
        }

        BestallningEntity.new {
            name = "testNamn2"
            type = "AF32001"
            personnummer = "20121212-1212"
        }
    }
}

object DBMigration {

    private fun getFlywayConfig(dataSource: HikariDataSource): FluentConfiguration {
        val flywayConfig = FluentConfiguration()
        flywayConfig.dataSource(dataSource)
        flywayConfig.schemas(dataSource.schema)
        flywayConfig.locations("db/migration/")
        return flywayConfig
    }

    fun migrate(dataSource: HikariDataSource) {
        val flyway = Flyway(getFlywayConfig(dataSource))
        flyway.migrate()
    }

    fun clean(dataSource: HikariDataSource) {
        val flyway = Flyway(getFlywayConfig(dataSource))
        flyway.clean();
    }
}
