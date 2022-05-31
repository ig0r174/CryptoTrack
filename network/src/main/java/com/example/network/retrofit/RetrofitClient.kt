package com.example.network.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Named

class RetrofitClient @Inject constructor(){
    private val client = OkHttpClient.Builder().addInterceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("X-CMC_PRO_API_KEY", "b32641ff-9746-448e-91ad-a493bb96f5e5")
            .build()
        chain.proceed(newRequest)
    }.build();

    fun getClient(baseUrl: String): Retrofit =
        Retrofit.Builder().client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}