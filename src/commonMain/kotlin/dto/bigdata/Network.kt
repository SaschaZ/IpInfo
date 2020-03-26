package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class Network(
    val registry: String,
    val registryStatus: String,
    val registeredCountry: String,
    val registeredCountryName: String,
    val organisation: String,
    val isReachableGlobally: Boolean,
    val isBogon: Boolean,
    val bgpPrefix: String,
    val bgpPrefixNetworkAddress: String,
    val bgpPrefixLastAddress: String,
    val totalAddresses: Int,
    val carriers: List<Carrier>,
    val viaCarriers: List<ViaCarrier>
)