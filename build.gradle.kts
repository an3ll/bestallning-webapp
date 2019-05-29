import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import se.an3ll.bestallning.build.Dependencies.exposedVersion
import se.an3ll.bestallning.build.Dependencies.flywayVersion
import se.an3ll.bestallning.build.Dependencies.hikariVersion
import se.an3ll.bestallning.build.Dependencies.koinVersion
import se.an3ll.bestallning.build.Dependencies.kotlinVersion
import se.an3ll.bestallning.build.Dependencies.ktorVersion
import se.an3ll.bestallning.build.Dependencies.logbackVersion
import se.an3ll.bestallning.build.Dependencies.mockkVersion
import se.an3ll.bestallning.build.Dependencies.postgresVersion
import se.an3ll.bestallning.build.Dependencies.spekVersion

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
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

  //ktor
  implementation("io.ktor:ktor-server-core:$ktorVersion")

  //ktor features
  implementation("io.ktor:ktor-server-tomcat:$ktorVersion")
  implementation("io.ktor:ktor-auth:$ktorVersion")
  implementation("io.ktor:ktor-gson:$ktorVersion")
  implementation("org.koin:koin-ktor:$koinVersion")
  implementation("io.ktor:ktor-html-builder:$ktorVersion")

  //logging
  implementation("ch.qos.logback:logback-classic:$logbackVersion")

  //db
  implementation("org.postgresql:postgresql:$postgresVersion")
  implementation("org.jetbrains.exposed:exposed:$exposedVersion")
  implementation("com.zaxxer:HikariCP:$hikariVersion")
  implementation("org.flywaydb:flyway-core:$flywayVersion")

  //test dependencies
  testImplementation("io.ktor:ktor-server-tests:$ktorVersion")

  //mockK
  testImplementation("io.mockk:mockk:$mockkVersion")

  //spek
  testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
  testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
  testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
  testCompile(kotlin("script-runtime"))
}

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

  withType<Test> {
    useJUnitPlatform {
      includeEngines("spek2")
    }
  }
}
