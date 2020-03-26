package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class ViaCarrier(
    val asn: String,
    val asnNumeric: Int,
    val organisation: String,
    val registeredCountry: String,
    val registeredCountryName: String,
    val totalIpv4Addresses: Int,
    val rank: Int
)