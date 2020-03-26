package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Currency(
    val numericCode: Int,
    val code: String,
    val name: String,
    val minorUnits: Int
)