import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val logback_version: String by project
val ktor_version: String by project
val kotlin_version: String by project

val koin_version = "2.0.0-rc-3"
val ktor_features_version = "0.0.8"
val postgres_version = "42.2.5"
val exposed_version = "0.13.6"
val hikari_version = "3.3.1"
val flyway_version = "6.0.0-beta"

plugins {
    application
    kotlin("jvm") version "1.3.30"
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
    implementation("io.ktor:ktor-server-tomcat:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")

    //logging
    implementation("ch.qos.logback:logback-classic:$logback_version")

    //json
    implementation("io.ktor:ktor-jackson:$ktor_version")

    //di
    implementation("org.koin:koin-ktor:$koin_version")

    //db
    compile("org.postgresql:postgresql:$postgres_version")
    compile("org.jetbrains.exposed:exposed:$exposed_version")
    compile("com.zaxxer:HikariCP:$hikari_version")
    compile("org.flywaydb:flyway-core:$flyway_version")


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
}
