import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import se.an3ll.bestallning.build.Dependencies

plugins {
  application
  kotlin("jvm") version "1.3.30"
  id("com.github.johnrengelman.shadow") version "5.0.0"
  id("io.gitlab.arturbosch.detekt") version "1.0.0-RC14"
}

group = "bestallning"
version = "0.0.1-SNAPSHOT"

application {
  mainClassName = "io.ktor.server.tomcat.EngineMain"
}

detekt {
  toolVersion = "1.0.0-RC14"
  input = files("src")
}

repositories {
  mavenLocal()
  jcenter()
  maven { url = uri("https://jitpack.io") }
  maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {

  //kotlin
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Dependencies.kotlinVersion}")

  //ktor
  implementation("io.ktor:ktor-server-core:${Dependencies.ktorVersion}")

  //ktor features
  implementation("io.ktor:ktor-server-tomcat:${Dependencies.ktorVersion}")
  implementation("io.ktor:ktor-auth:${Dependencies.ktorVersion}")
  implementation("io.ktor:ktor-jackson:${Dependencies.ktorVersion}")
  implementation("org.koin:koin-ktor:${Dependencies.koinVersion}")
  implementation("io.ktor:ktor-html-builder:${Dependencies.ktorVersion}")

  //serialization
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${Dependencies.jacksonVersion}")


  //logging
  implementation("ch.qos.logback:logback-classic:${Dependencies.logbackVersion}")

  //db
  implementation("org.postgresql:postgresql:${Dependencies.postgresVersion}")
  implementation("org.jetbrains.exposed:exposed:${Dependencies.exposedVersion}")
  implementation("com.zaxxer:HikariCP:${Dependencies.hikariVersion}")
  implementation("org.flywaydb:flyway-core:${Dependencies.flywayVersion}")


  //test
  testImplementation("io.ktor:ktor-server-tests:${Dependencies.ktorVersion}")
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
