package se.an3ll.bestallning.app.routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import org.koin.ktor.ext.inject
import se.an3ll.bestallning.app.services.BestallningService

fun Application.bestallningRoutes() {

    val bestallningService: BestallningService by inject()

    routing {
        route("/bestallningar") {
            get {
                call.respond(mapOf("hello" to bestallningService.listBestallningar()))
            }
        }
    }
}

fun Application.userRoutes() {

    routing {
        route("/users") {
            get {
                call.respond(mapOf("hello" to "användaren"))
            }
        }
    }
}
