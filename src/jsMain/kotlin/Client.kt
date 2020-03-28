import dto.IpIfy4Json
import dto.IpIfy6Json
import dto.IpInfo
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.browser.document
import kotlin.coroutines.CoroutineContext

class Client {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    private val provider = DataProvider(client)

    private val scope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext = Dispatchers.Default
    }

    init {
        document.addEventListener("DOMContentLoaded", {
            scope.launch {
                val ipv4Async = async { provider.provide(IpIfy4Json) }
                val ipv6Async = async { provider.provide(IpIfy6Json) }

                val ipv4 = ipv4Async.await().toString()
                val ipv6 = ipv6Async.await().toString()

                document.getElementById("ipv4")?.textContent = ipv4
                if (ipv6 != ipv4)
                    document.getElementById("ipv6")?.textContent = ipv6

                client.post<IpInfo>(document.location?.href ?: "/") {
                    body = IpInfo(ipv4, ipv6)
                }
            }
        })
    }
}

fun main() {
    Client()
}