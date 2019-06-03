package se.an3ll.bestallning.app.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.ApplicationEnvironment

object DataSource {

  fun dataSource(environment: ApplicationEnvironment): HikariDataSource {

    val config = HikariConfig()
    config.schema = environment.config.property("ktor.postgres.schema").getString()
    config.driverClassName = "org.postgresql.Driver"
    config.jdbcUrl = environment.config.property("ktor.postgres.jdbcUrl").getString()
    config.maximumPoolSize = environment.config.property("ktor.postgres.maximumPoolSize").getString().toInt()
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    config.username = environment.config.property("ktor.postgres.username").getString()
    config.password = environment.config.propertyOrNull("ktor.postgres.password")?.getString()
    config.validate()
    return HikariDataSource(config)
  }

  fun devDataSource(): HikariDataSource {
    val config = HikariConfig()
    config.driverClassName = "org.h2.Driver"
    config.jdbcUrl = "jdbc:h2:mem:test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;"
    config.username = "sa"
    config.maximumPoolSize = 3
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    config.validate()
    return HikariDataSource(config)
  }

}
