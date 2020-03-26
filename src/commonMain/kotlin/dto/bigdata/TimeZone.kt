package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class TimeZone(
    val ianaTimeId: String,
    val displayName: String,
    val effectiveTimeZoneFull: String,
    val effectiveTimeZoneShort: String,
    val utcOffsetSeconds: Int,
    val utcOffset: String,
    val isDaylightSavingTime: Boolean,
    val localTime: String
)