package com.example.domain.useCases.GetCoins

import com.example.domain.model.CoinModel

interface IGetCoinsUseCase {
    suspend fun getCoins(start: Int, limit: Int, convert: String): List<CoinModel>
}