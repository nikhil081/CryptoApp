package com.example.cryptoapp


import com.example.cryptoapp.model.Coin
import com.example.cryptoapp.model.CoinsListingResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("api/v3/coins/markets")
    fun getDetails( @Query("vs_currency") vs_currency: String?,
                    @Query("order") order: String?,
                    @Query("per_page") per_page: Int?): Single<CoinsListingResponse>
}