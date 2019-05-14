package se.an3ll.bestallning.app.routes

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.client.request.request
import io.ktor.http.Parameters
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import org.koin.ktor.ext.inject
import se.an3ll.bestallning.app.domain.BestallningStatus
import se.an3ll.bestallning.app.services.BestallningService

fun Application.bestallningRoutes() {

  val bestallningService: BestallningService by inject()

  routing {
    authenticate {
      route("/bestallningar") {
        get {
          val params = call.request.queryParameters

          val query = ListBestallningarQuery(
            statusar = listOf(BestallningStatus.OLAST),
            hsaId = "hsaId",
            orgNrVardgivare = "org",
            textSearch = params["textSearch"],
            pageIndex = 0,
            limit = 10,
            sortColumn = ListBestallningSortColumn.ANKOMST_DATUM,
            sortDirection = ListBestallningDirection.ASC
          )

          call.respond(bestallningService.listByQuery(query))
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

data class MySession(val user: String)

