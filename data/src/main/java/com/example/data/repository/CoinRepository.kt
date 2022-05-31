package com.example.data.repository

import com.example.domain.model.CoinModel
import com.example.domain.model.*
import com.example.domain.repository.ICoinsRepository
import com.example.network.retrofit.ApiProvider
import javax.inject.Inject

class CoinRepository @Inject constructor(
    private val apiProvider: ApiProvider
) : ICoinsRepository {
    override suspend fun getCoins(start: Int, limit: Int, convert: String): List<CoinModel> {
        return mapToCoinModels(apiProvider.getApi().getCoinList(start, limit, convert))
    }

    private fun mapToCoinModels(coinList: CoinListDataModel): List<CoinModel> {
        return coinList.data.map {
            CoinModel(
                id = it.id,
                name = it.name,
                symbol = it.symbol,
                quote = it.quote
            )
        }
    }

}