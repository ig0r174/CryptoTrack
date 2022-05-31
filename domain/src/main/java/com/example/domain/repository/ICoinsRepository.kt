package com.example.domain.repository

import com.example.domain.model.CoinModel

interface ICoinsRepository {
    suspend fun getCoins(start: Int, limit: Int, convert: String): List<CoinModel>
}