package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Location(
    val isoPrincipalSubdivision: String,
    val isoPrincipalSubdivisionCode: String,
    val city: String,
    val postcode: String,
    val latitude: Double,
    val longitude: Double,
    val timeZone: TimeZone,
    val localityInfo: LocalityInfo
)