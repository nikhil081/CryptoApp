package com.example.cryptoapp

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    private val BASE_URL = "https://api.lunarcrush.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getDetails(data: String, symbol: String): Single<model> {
        return api.getDetails(data, symbol, "vza9rb6fsohi2bbffb34g")
    }
}