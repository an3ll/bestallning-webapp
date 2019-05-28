## KTOR-POC-WEBAPP

This is a simple webapp using the Ktor framework and Kotlin.

## Tech stack
* Language: Kotlin (targeting jvm)- https://kotlinlang.org/
* Database abstraction layer: Exposed Framework - https://github.com/JetBrains/Exposed
* Database migration tool: https://flywaydb.org/
* Dependency Injection: Koin - https://insert-koin.io/
* Build tool: Gradle - https://gradle.org/
* Code Lint tool: Detekt: https://arturbosch.github.io/detekt/

## Installation
To run and build the application, you need to have 
jdk8 or later installed on your development machine

## Get started
To build the application:
```
./gradlew clean build
```

To run the application from gradle:
```
./gradlew run
```

The application will expose the rest endpoint at:
```
http://localhost:8080/
```

## Configuration
All the configurations for the application can be found in the file:
[src/main/resources/application.conf](src/main/resources/application.conf)

### Database configuration

If the parameter ktor.environment is set to "dev" like below
```
ktor {
  environment = dev
}
```

The application will start with an embedded in memory database.

If the parameter is set to anything else the datasource will connect to a 
persistent postgres-database. 

To change the configuration for the datasource, see the file:
[src/main/kotlin/se/an3ll/bestallning/app/config/DataSource.kt](src/main/kotlin/se/an3ll/bestallning/app/config/DataSource.kt)
