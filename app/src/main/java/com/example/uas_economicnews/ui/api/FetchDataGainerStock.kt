package com.example.uas_economicnews.ui.api

import com.example.uas_economicnews.ui.response.GainerResponse
import retrofit2.Response
import retrofit2.http.GET


interface FetchDataGainerStock {
    @GET("/query?function=TOP_GAINERS_LOSERS&apikey=C7U1Y6514YQVQFNT")
    suspend fun getData(): Response<GainerResponse>
}
