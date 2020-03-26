package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Administrative(
    val order: Int,
    val adminLevel: Int,
    val name: String,
    val description: String,
    val isoName: String,
    val isoCode: String,
    val wikidataId: String
)