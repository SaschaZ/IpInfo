package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class ConfidenceArea(
    val latitude: Double,
    val longitude: Double
)