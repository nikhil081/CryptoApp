package com.example.cryptoapp

import com.example.cryptoapp.model.CoinsListingResponse
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    private val BASE_URL = "https://api.coingecko.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getDetails(): Single<CoinsListingResponse> {
        return api.getDetails("usd","market_cap_desc",100)
    }
}