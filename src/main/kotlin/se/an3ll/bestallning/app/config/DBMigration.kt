package se.an3ll.bestallning.app.config

import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.configuration.FluentConfiguration

object DBMigration {

  private fun getFlywayConfig(dataSource: HikariDataSource): FluentConfiguration {
    val flywayConfig = FluentConfiguration()
    flywayConfig.dataSource(dataSource)

    dataSource.schema?.let {
      flywayConfig.schemas(dataSource.schema)
    }

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
