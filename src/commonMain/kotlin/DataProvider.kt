import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

interface IDataSource<T> {

    suspend fun HttpClient.provide(): T
}

class DataProvider(client: HttpClient? = null) {

    private val client = client ?: HttpClient() {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun <T: Any> provide(source: IDataSource<T>): T = source.run { client.provide() }
}