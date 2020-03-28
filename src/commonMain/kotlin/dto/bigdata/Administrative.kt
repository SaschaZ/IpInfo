package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Administrative(
    val order: Int,
    val adminLevel: Int,
    val name: String,
    val description: String? = null,
    val isoName: String? = null,
    val isoCode: String? = null,
    val wikidataId: String? = null
)