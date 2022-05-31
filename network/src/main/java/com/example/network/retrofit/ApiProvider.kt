package com.example.network.retrofit

import javax.inject.Inject

class ApiProvider @Inject constructor(
    private val client: RetrofitClient
) {
    private val baseUrl = "https://pro-api.coinmarketcap.com/v1/"

    fun getApi() : IApi =
        client.getClient(baseUrl)
            .create(IApi::class.java)
}