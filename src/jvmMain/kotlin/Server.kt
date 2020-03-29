@file:Suppress("EXPERIMENTAL_API_USAGE")


import dto.IpInfo
import dto.bigdata.BigData
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.html.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(CORS) {
            method(HttpMethod.Options)
            method(HttpMethod.Get)
            method(HttpMethod.Post)
            method(HttpMethod.Put)
            method(HttpMethod.Delete)
            method(HttpMethod.Patch)
            header(HttpHeaders.Authorization)
            allowCredentials = true
            anyHost()
        }

        install(ContentNegotiation) {
            json(
                contentType = ContentType.Application.Json,
                json = Json(
                    DefaultJsonConfiguration.copy(
                        prettyPrint = true
                    )
                )
            )
        }

        val dataProvider = DataProvider()

        routing {
            get("/") {
                call.respondHtml {
                    head {
                        title("Ip Info")
                    }
                    body {
                        h1 {
                            id = "ipv4"
                            +"Loading..."
                        }
                        h1 {
                            id = "ipv6"
                        }
                        p {
                            id = "bigData"
                        }
                        script(src = "/static/IpInfo.js") {}
                    }
                }
            }

            post("/") {
                val info = call.receive<IpInfo>()
                val bigData = dataProvider.provide(BigData, info.ipv4)
                println(bigData)
                call.respond(bigData)
            }

            static("/static") {
                resource("IpInfo.js")
            }
        }
    }.start(wait = true)
}