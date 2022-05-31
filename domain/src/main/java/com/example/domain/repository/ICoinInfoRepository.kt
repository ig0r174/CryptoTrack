package com.example.domain.repository

import com.example.domain.model.CoinDetailModel
import com.example.domain.model.CoinModel

interface ICoinInfoRepository {
    suspend fun getCoinInfo(coinId: Int): CoinDetailModel
}