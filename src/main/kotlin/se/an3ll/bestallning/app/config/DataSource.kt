package se.an3ll.bestallning.app.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object DataSource {

  fun dataSource(): HikariDataSource {
    val config = HikariConfig()
    config.schema = "public"
    config.driverClassName = "org.postgresql.Driver"
    config.jdbcUrl = "jdbc:postgresql:bestallning"
    config.maximumPoolSize = 3
    config.isAutoCommit = false
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    config.username = "user"
    config.password = "password"
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
