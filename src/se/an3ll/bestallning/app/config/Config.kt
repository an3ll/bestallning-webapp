package se.an3ll.bestallning.app.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.FluentConfiguration
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.slf4j.LoggerFactory
import se.an3ll.bestallning.app.routes.MySession
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
      registerModule(JavaTimeModule())
    }
  }

  install(Koin) {
    modules(applicationModule)
  }

  install(Sessions) {
    cookie<MySession>("APP_SESSION", SessionStorageMemory())
  }

  install(Authentication) {
    basic {
      realm = "application"
      validate { credentials ->
        if (credentials.password == "password"
          && credentials.name == "admin"
        ) UserIdPrincipal(credentials.name)
        else null
      }
    }

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

  val log = LoggerFactory.getLogger(Application::class.java)

  val dataSource = dataSource()

  val profile = ConfigFactory.load().getString("profile")

  if (profile == "dev") {
    DBMigration.clean(dataSource)
  }

  DBMigration.migrate(dataSource)
  Database.connect(dataSource)

  if (profile == "dev") {
    transaction {
      val bestallningar = bootstrapBestallningar()
      log.info("bootstrapped: $bestallningar")
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
    flyway.clean()
  }
}
