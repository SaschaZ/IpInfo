@file:Suppress("EXPERIMENTAL_API_USAGE")


import dto.IpInfo
import dto.IpInfoSession
import dto.bigdata.BigData
import io.ktor.application.ApplicationCall
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
import io.ktor.sessions.*
import io.ktor.util.hex
import kotlinx.html.*
import kotlinx.serialization.json.Json

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
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

        install(Sessions) {
            cookie<IpInfoSession>("SESSION") {
                val secretSignKey = hex("000102030405060708090a0b0c0d0e0f")
                transform(SessionTransportTransformerMessageAuthentication(secretSignKey))

                serializer = object : SessionSerializer<IpInfoSession> {

                    val ser = IpInfoSession.serializer()

                    override fun deserialize(text: String): IpInfoSession =
                        Json.parse(ser, text)

                    override fun serialize(session: IpInfoSession): String =
                        Json.stringify(ser, session)
                }
            }
        }

        val dataProvider = DataProvider()

        routing {
            get("/") {
                call.increaseVisitCount()
                call.respondHtml {
                    head {
                        title("Ip Info")
                    }
                    body {
                        p { +"${call.visitCount}" }
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
                call.apply {
                    val info = receive<IpInfo>()
                    ipv4 = info.ipv4
                    ipv6 = info.ipv6
                    bigData = dataProvider.provide(BigData, info.ipv4)
                    println(session)
                    respond(bigData!!)
                }
            }

            static("/static") {
                resource("IpInfo.js")
            }
        }
    }.start(wait = true)
}

private fun ApplicationCall.increaseVisitCount() {
    ++visitCount
}

var ApplicationCall.ipv4: String?
    get() = session.ipv4
    set(value) {
        session = session.copy(ipv4 = value)
    }

var ApplicationCall.ipv6: String?
    get() = session.ipv6
    set(value) {
        session = session.copy(ipv6 = value)
    }

var ApplicationCall.bigData: BigData?
    get() = session.bigData
    set(value) {
        session = session.copy(bigData = value)
    }

var ApplicationCall.visitCount: Int
    get() = session.visitCount
    set(value) {
        session = session.copy(visitCount = value)
    }

var ApplicationCall.session: IpInfoSession
    get() = sessions.get<IpInfoSession>() ?: IpInfoSession()
    set(session) = sessions.set(session)