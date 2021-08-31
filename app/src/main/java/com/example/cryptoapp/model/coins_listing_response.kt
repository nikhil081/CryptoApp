package com.example.cryptoapp.model

data class CoinsListing(
    var data: List<Coin>?,
    var status: Status?
)

data class Coin(
    var circulating_supply: Double?,
    var cmc_rank: Int?,
    var date_added: String?,
    var id: Int?,
    var last_updated: String?,
    var max_supply: Double?,
    var name: String?,
    var num_market_pairs: Int?,
    var platform: Any?,
    var quote: Quote?,
    var slug: String?,
    var symbol: String?,
    var tags: List<String>?,
    var total_supply: Double?
)

data class Status(
    var credit_count: Int?,
    var elapsed: Int?,
    var error_code: Int?,
    var error_message: Any?,
    var notice: Any?,
    var timestamp: String?,
    var total_count: Int?
)

data class Quote(
    var USD: USD?
)

data class USD(
    var fully_diluted_market_cap: Double?,
    var last_updated: String?,
    var market_cap: Double?,
    var market_cap_dominance: Double?,
    var percent_change_1h: Double?,
    var percent_change_24h: Double?,
    var percent_change_30d: Double?,
    var percent_change_60d: Double?,
    var percent_change_7d: Double?,
    var percent_change_90d: Double?,
    var price: Double?,
    var volume_24h: Double?
)