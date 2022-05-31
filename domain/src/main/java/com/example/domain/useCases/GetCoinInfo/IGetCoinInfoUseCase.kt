package com.example.domain.useCases.GetCoinInfo

import com.example.domain.model.CoinDetailModel
import com.example.domain.model.CoinModel

interface IGetCoinInfoUseCase {
    suspend fun getCoinInfo(coinId: Int): CoinDetailModel
}