package dto

import kotlinx.serialization.Serializable

@Serializable
data class IpInfo(
    val ipv4: String,
    val ipv6: String
)