package dto.bigdata

import IDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

@Serializable
data class BigData(
    val ip: String,
    val localityLanguageRequested: String,
    val isReachableGlobally: Boolean,
    val country: Country,
    val location: Location,
    val lastUpdated: String,
    val network: Network,
    val confidence: String,
    val confidenceArea: List<ConfidenceArea>,
    val securityThreat: String,
    val hazardReport: HazardReport
) {
    companion object : IDataSource<BigData> {
        private const val url = "https://api.bigdatacloud.net/data/ip-geolocation-full?key=0a73bcab27714bac8c91c6e9cca5018d"

        override suspend fun HttpClient.provide(): BigData = get(url)
    }
}

