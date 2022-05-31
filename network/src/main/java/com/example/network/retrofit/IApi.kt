package com.example.network.retrofit

import com.example.domain.model.CoinDetailDataModel
import com.example.domain.model.CoinListDataModel
import retrofit2.http.*

interface IApi
{
    @GET("/v1/cryptocurrency/listings/latest?")
    suspend fun getCoinList(@Query("start") start: Int, @Query("limit") limit: Int, @Query("convert") convert: String): CoinListDataModel

    @GET("/v1/cryptocurrency/info?")
    suspend fun getCoinInfo(@Query("id") id: Int): CoinDetailDataModel
}