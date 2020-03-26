package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class WbRegion(
    val id: String,
    val iso2Code: String,
    val value: String
)