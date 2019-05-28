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
