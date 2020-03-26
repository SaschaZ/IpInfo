package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Carrier(
    val asn: String,
    val asnNumeric: Int,
    val organisation: String,
    val name: String,
    val registry: String,
    val registeredCountry: String,
    val registeredCountryName: String,
    val registrationDate: String,
    val registrationLastChange: String,
    val totalIpv4Addresses: Int,
    val totalIpv4Prefixes: Int,
    val totalIpv4BogonPrefixes: Int,
    val rank: Int,
    val rankText: String
)