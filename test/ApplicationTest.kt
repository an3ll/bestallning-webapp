package se.an3ll

import io.ktor.http.HttpMethod
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import se.an3ll.bestallning.app.module
import kotlin.test.Test
import kotlin.test.assertNull

class ApplicationTest {

    @Test
    fun testRoot() {
        withTestApplication({ module() }) {
            handleRequest(HttpMethod.Get, "/").apply {
                assertNull(response.status())
            }
        }
    }
}
