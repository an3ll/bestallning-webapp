package se.an3ll.bestallning.app.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.application.Application
import io.ktor.application.ApplicationEnvironment
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.basic
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.slf4j.LoggerFactory
import se.an3ll.bestallning.app.config.BootstrapData.bootstrapBestallningar
import se.an3ll.bestallning.app.config.DataSource.dataSource
import se.an3ll.bestallning.app.config.DataSource.devDataSource
import se.an3ll.bestallning.app.routes.MySession
import se.an3ll.bestallning.app.routes.bestallningRoutes
import se.an3ll.bestallning.app.routes.userRoutes
import se.an3ll.bestallning.app.services.BestallningService
import se.an3ll.bestallning.app.services.BestallningServiceImpl

fun Application.config() {
  features()

  initDb(environment)

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



fun initDb(environment: ApplicationEnvironment) {

  val log = LoggerFactory.getLogger(Application::class.java)

  when (environment.config.property("ktor.environment").getString()) {
    "dev" -> {
      val dataSource = devDataSource()
      DBMigration.clean(dataSource)
      DBMigration.migrate(dataSource)
      Database.connect(dataSource)

      transaction {
        val bestallningar = bootstrapBestallningar()
        log.info("bootstrapped: $bestallningar")
      }
    }
    else -> {
      val dataSource = dataSource()
      DBMigration.migrate(dataSource)
      Database.connect(dataSource)
    }
  }
}
