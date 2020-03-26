package dto.bigdata

import kotlinx.serialization.Serializable


@Serializable
data class HazardReport(
    val isKnownAsTorServer: Boolean,
    val isKnownAsVpn: Boolean,
    val isKnownAsProxy: Boolean,
    val isSpamhausDrop: Boolean,
    val isSpamhausEdrop: Boolean,
    val isSpamhausAsnDrop: Boolean,
    val isBlacklistedUceprotect: Boolean,
    val isBlacklistedBlocklistDe: Boolean,
    val isKnownAsMailServer: Boolean,
    val isKnownAsPublicRouter: Boolean,
    val isBogon: Boolean,
    val isUnreachable: Boolean,
    val hostingLikelihood: Int,
    val isHostingAsn: Boolean,
    val isCellular: Boolean
)