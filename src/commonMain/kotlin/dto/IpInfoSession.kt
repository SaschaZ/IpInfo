package dto

import dto.bigdata.BigData
import kotlinx.serialization.Serializable

@Serializable
data class IpInfoSession(
    val visitCount: Int = 0,
    val ipv4: String? = null,
    val ipv6: String? = null,
    val bigData: BigData? = null
)