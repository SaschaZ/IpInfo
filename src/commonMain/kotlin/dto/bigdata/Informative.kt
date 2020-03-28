package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Informative(
    val order: Int,
    val name: String,
    val description: String? = null,
    val isoCode: String? = null,
    val wikidataId: String? = null
)