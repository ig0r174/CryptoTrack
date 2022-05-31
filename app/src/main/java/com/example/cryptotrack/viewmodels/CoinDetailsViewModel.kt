package com.example.cryptotrack.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.CoinDetailModel
import com.example.domain.useCases.GetCoinInfo.GetCoinInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinDetailsViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase
) : ViewModel() {

    val coinInfo = MutableLiveData<CoinDetailModel>()

    fun refreshData(coinId: Int) {
        viewModelScope.launch {
            coinInfo.value = getCoinInfoUseCase.getCoinInfo(coinId);
        }
    }

}