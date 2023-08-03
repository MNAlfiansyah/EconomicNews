package com.example.uas_economicnews.ui.api

import com.example.uas_economicnews.ui.response.GainerResponse
import retrofit2.Response
import retrofit2.http.GET


interface FetchDataGainerForex {
    @GET("/query?function=TOP_GAINERS_LOSERS&apikey=U7NDT51U0HP34W9H")
    suspend fun getData(): Response<GainerResponse>
}
