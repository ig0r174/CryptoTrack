package com.example.cryptotrack.viewmodels

import androidx.lifecycle.ViewModel
import com.example.coinmarketcapexample.repository.CoinListRepository
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository) : ViewModel() {
    fun fetchData() = coinListRepository.fetchData()
    fun loadMore() = coinListRepository.loadMore()
}