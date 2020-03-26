package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class LocalityInfo(
    val administrative: List<Administrative>,
    val informative: List<Informative>
)