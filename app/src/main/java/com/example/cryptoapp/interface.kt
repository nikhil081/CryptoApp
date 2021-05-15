package com.example.cryptoapp


import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    companion object {
        var BASE_URL = "https://api.lunarcrush.com"
        var API_KEY = "vza9rb6fsohi2bbffb34g"
    }

    @GET("v2")
    fun getDetails(
        @Query("data") data: String?,
        @Query("symbol") symbol: String?,
        @Query("key") key: String?
    ): Single<model>
}