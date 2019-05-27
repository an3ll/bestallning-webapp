package se.an3ll.bestallning.app

import io.ktor.config.MapApplicationConfig
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

object ApplicationTest : Spek({

  group("Application Integration Tests") {

    test("Get bestallningar when not authorized") {

      withTestApplication({
        (environment.config as MapApplicationConfig).apply {
          put("ktor.environment", "dev")
          put("ktor.deployment.port", "8080")
          put("ktor.application.modules", listOf("se.an3ll.bestallning.app.ApplicationKt.module"))
        }
        module()
      }) {

        handleRequest(HttpMethod.Get, "/bestallningar").apply {
          assertEquals(HttpStatusCode.Unauthorized, response.status())
        }
      }
    }
  }
})
