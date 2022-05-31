package com.example.domain.model

data class CoinModel(
    val id: Int,
    val name: String,
    val symbol: String,
    val quote: Map<String, CurrencyData>
)
{
    data class CurrencyData(
        val price: Double,
        val percent_change_24h: Double,
        val market_cap: Double,
        val market_cap_dominance: Double
    )
}