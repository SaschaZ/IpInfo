package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class IsoAdminLanguage(
    val isoAlpha3: String,
    val isoAlpha2: String,
    val isoName: String,
    val nativeName: String
)