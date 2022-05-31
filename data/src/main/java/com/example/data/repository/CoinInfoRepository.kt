package com.example.data.repository

import com.example.domain.model.CoinModel
import com.example.domain.model.*
import com.example.domain.repository.ICoinInfoRepository
import com.example.domain.repository.ICoinsRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class CoinInfoRepository @Inject constructor(
    private val apiProvider: ApiProvider
) : ICoinInfoRepository {
    override suspend fun getCoinInfo(coinId: Int): CoinDetailModel {
        return mapToCoinModel(apiProvider.getApi().getCoinInfo(coinId), coinId)
    }

    private fun mapToCoinModel(coin: CoinDetailDataModel, coinId: Int): CoinDetailModel {
        return CoinDetailModel(
                id = coin.data[coinId.toString()]!!.id,
                name = coin.data[coinId.toString()]!!.name,
                symbol = coin.data[coinId.toString()]!!.symbol,
                logo = coin.data[coinId.toString()]!!.logo,
                description = coin.data[coinId.toString()]!!.description,
                urls = coin.data[coinId.toString()]!!.urls
            )
    }

}