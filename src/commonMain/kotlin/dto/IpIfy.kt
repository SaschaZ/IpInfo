@file:Suppress("unused")

package dto

import IDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

@Serializable
data class IpIfy(val ip: String) {
    override fun toString() = ip
}

object IpIfy6Plain : IDataSource<String> {
    private const val url = "https://api6.ipify.org"

    override suspend fun HttpClient.provide(): String = get(url)
}

object IpIfy4Plain : IDataSource<String> {
    private const val url = "https://api.ipify.org"

    override suspend fun HttpClient.provide(): String = get(url)
}

object IpIfy6Json : IDataSource<IpIfy> {
    private const val url = "https://api6.ipify.org?format=json"

    override suspend fun HttpClient.provide(): IpIfy = get(url)
}

object IpIfy4Json : IDataSource<IpIfy> {
    private const val url = "https://api.ipify.org?format=json"

    override suspend fun HttpClient.provide(): IpIfy = get(url)
}