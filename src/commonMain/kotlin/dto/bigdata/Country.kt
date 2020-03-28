package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Country(
    val isoAlpha2: String,
    val isoAlpha3: String,
    val m49Code: Int,
    val name: String,
    val isoName: String? = null,
    val isoAdminLanguages: List<IsoAdminLanguage>,
    val unRegion: String,
    val currency: Currency,
    val wbRegion: WbRegion,
    val wbIncomeLevel: WbIncomeLevel,
    val callingCode: String,
    val countryFlagEmoji: String
)