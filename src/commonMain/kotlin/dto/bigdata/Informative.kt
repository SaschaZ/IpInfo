package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Informative(
    val order: Int,
    val name: String,
    val description: String,
    val isoCode: String,
    val wikidataId: String
)