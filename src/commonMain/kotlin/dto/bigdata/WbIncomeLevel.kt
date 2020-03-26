package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class WbIncomeLevel(
    val id: String,
    val iso2Code: String,
    val value: String
)