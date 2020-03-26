
import dto.IpIfy4Plain
import dto.IpIfy6Plain
import kotlinx.coroutines.*
import kotlin.browser.document
import kotlin.coroutines.CoroutineContext

class Client {

    private val provider = DataProvider()

    private val scope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext = Dispatchers.Default
    }

    init {
        document.addEventListener("DOMContentLoaded", {
            scope.launch {
                val ipv4Async = async { provider.provide(IpIfy4Plain) }
                val ipv6Async = async { provider.provide(IpIfy6Plain) }

                val ipv4 = ipv4Async.await().toString()
                val ipv6 = ipv6Async.await().toString()

                document.getElementById("ipv4")?.textContent = ipv4
                if (ipv6 != ipv4)
                    document.getElementById("ipv6")?.textContent = ipv6
            }
        })
    }
}

fun main() {
    Client()
}