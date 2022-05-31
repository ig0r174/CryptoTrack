package com.example.coinmarketcapexample.repository

import com.example.domain.model.CoinListDataModel
import com.example.domain.repository.BaseRepository

abstract class CoinListRepository : BaseRepository<CoinListDataModel>() {
    abstract fun loadMore()
    abstract fun fetchData()
}