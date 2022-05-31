package com.example.domain.repository

import com.example.domain.model.CoinDetailDataModel
import com.example.domain.repository.BaseRepository

abstract class CoinDetailRepository : BaseRepository<CoinDetailDataModel>() {
    abstract fun fetchData(id: Int)
}