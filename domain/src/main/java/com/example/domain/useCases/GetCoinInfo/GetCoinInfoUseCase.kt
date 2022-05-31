package com.example.domain.useCases.GetCoinInfo

import com.example.domain.model.CoinDetailModel
import com.example.domain.repository.ICoinInfoRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repo: ICoinInfoRepository
): IGetCoinInfoUseCase {
    override suspend fun getCoinInfo(coinId: Int): CoinDetailModel {
        return repo.getCoinInfo(coinId);
    }
}