package com.example.domain.useCases.GetCoins

import com.example.domain.model.CoinModel
import com.example.domain.repository.ICoinsRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repo: ICoinsRepository
): IGetCoinsUseCase {
    override suspend fun getCoins(start: Int, limit: Int, convert: String): List<CoinModel> {
        return repo.getCoins(start, limit, convert);
    }

}