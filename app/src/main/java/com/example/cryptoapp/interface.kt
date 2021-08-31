package com.example.cryptoapp


import com.example.cryptoapp.model.Coin
import com.example.cryptoapp.model.CoinsListing
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface Api {
    @Headers("X-CMC_PRO_API_KEY: d2014cf6-5886-42cb-8621-081eb0ea593f")
    @GET("v1/cryptocurrency/listings/latest")
    fun getDetails(): Single<CoinsListing>
}