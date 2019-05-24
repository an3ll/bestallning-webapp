package se.an3ll.bestallning.app

import io.ktor.application.Application
import se.an3ll.bestallning.app.config.config

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

fun Application.module() {
  config()
}
