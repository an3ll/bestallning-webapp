import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val logback_version: String by project
val ktor_version: String by project
val kotlin_version: String by project

val koin_version = "2.0.0-rc-3"
val ktor_features_version = "0.0.8"
val postgres_version = "42.2.5"
val exposed_version = "0.13.7"
val hikari_version = "3.3.1"
val flyway_version = "6.0.0-beta"
val jackson_version = "2.9.8"

plugins {
  application
  kotlin("jvm") version "1.3.30"
  id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "bestallning"
version = "0.0.1-SNAPSHOT"

application {
  mainClassName = "io.ktor.server.tomcat.EngineMain"
}

repositories {
  mavenLocal()
  jcenter()
  maven { url = uri("https://jitpack.io") }
  maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {

  //kotlin
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")

  //ktor
  implementation("io.ktor:ktor-server-core:$ktor_version")

  //ktor features
  implementation("io.ktor:ktor-server-tomcat:$ktor_version")
  implementation("io.ktor:ktor-auth:$ktor_version")
  implementation("io.ktor:ktor-jackson:$ktor_version")
  implementation("org.koin:koin-ktor:$koin_version")
  implementation("io.ktor:ktor-html-builder:$ktor_version")

  //serialization
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jackson_version")


  //logging
  implementation("ch.qos.logback:logback-classic:$logback_version")

  //db
  implementation("org.postgresql:postgresql:$postgres_version")
  implementation("org.jetbrains.exposed:exposed:$exposed_version")
  implementation("com.zaxxer:HikariCP:$hikari_version")
  implementation("org.flywaydb:flyway-core:$flyway_version")


  //test
  testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

tasks {
  withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
  }

  withType<Jar> {
    manifest {
      attributes(
        mapOf(
          "Main-Class" to application.mainClassName
        )
      )
    }
  }
}
