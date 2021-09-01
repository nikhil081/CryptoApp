package com.example.cryptoapp.model

class CoinsListingResponse : ArrayList<Coin>()

data class Coin(
    var ath: Double?,
    var ath_change_percentage: Double?,
    var ath_date: String?,
    var atl: Double?,
    var atl_change_percentage: Double?,
    var atl_date: String?,
    var circulating_supply: Double?,
    var current_price: Float?,
    var fully_diluted_valuation: Double?,
    var high_24h: Double?,
    var id: String?,
    var image: String?,
    var last_updated: String?,
    var low_24h: Double?,
    var market_cap: Double?,
    var market_cap_change_24h: Double?,
    var market_cap_change_percentage_24h: Double?,
    var market_cap_rank: Double?,
    var max_supply: Double?,
    var name: String?,
    var price_change_24h: Double?,
    var price_change_percentage_24h: Double?,
    var roi: Roi?,
    var symbol: String?,
    var total_supply: Double?,
    var total_volume: Double?
)

data class Roi(
    var currency: String?,
    var percentage: Double?,
    var times: Double?
)