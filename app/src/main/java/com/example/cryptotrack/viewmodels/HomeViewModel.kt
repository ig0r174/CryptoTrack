package com.example.cryptotrack.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.storage.IStorage
import com.example.domain.model.CoinModel


import com.example.domain.useCases.GetCoins.GetCoinsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
    private val storage: IStorage
): ViewModel() {
    val coins = MutableLiveData<List<CoinModel>>(emptyList())
    val coinsLimit = 20
    var page = 0

    fun refreshData() {
        viewModelScope.launch {
            var myCurrency = storage.getCurrency()

            if(myCurrency == null) {
                myCurrency = "RUB"
                storage.saveCurrency(myCurrency)
            }

            coins.value =
                coins.value?.plus(getCoinsUseCase.getCoins((page * coinsLimit + 1), coinsLimit, myCurrency));
        }
    }

    fun loadMore() {
        page++
        refreshData()
    }

    fun clean(){
        viewModelScope.launch {
            coins.postValue(emptyList())
        }
    }

}