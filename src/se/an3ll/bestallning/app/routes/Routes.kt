package se.an3ll.bestallning.app.routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.auth.UserIdPrincipal
import io.ktor.auth.authenticate
import io.ktor.auth.authentication
import io.ktor.html.respondHtml
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.routing.routing
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import kotlinx.html.*
import org.koin.ktor.ext.inject
import se.an3ll.bestallning.app.services.BestallningService

fun Application.bestallningRoutes() {

    val bestallningService: BestallningService by inject()

    routing {
        authenticate("equal-auth") {
            route("/bestallningar") {

                get {
                    call.respond(mapOf("hello" to bestallningService.listBestallningar()))
                }
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

fun Application.loginRoutes() {
    routing {
        get("/login") {
            call.respondHtml {
                body {
                    val message = call.parameters["message"] ?: ""
                    if (message.isNotEmpty()) {
                        div {
                            +message
                        }
                    }
                    form(
                        action = "/login",
                        encType = FormEncType.applicationXWwwFormUrlEncoded,
                        method = FormMethod.post
                    ) {
                        div {
                            +"Username:"
                            textInput(name = "username") { }
                        }
                        div {
                            +"Password:"
                            passwordInput(name = "password") { }
                        }
                        div {
                            submitInput()
                        }
                    }
                }
            }
        }
        authenticate("equal-auth") {
            post("/login") {
                val principal = call.authentication.principal<UserIdPrincipal>()
                call.sessions.set(MySession(principal!!.name))
                call.respondRedirect("/")
            }
        }
    }
}

data class MySession(val user: String)

