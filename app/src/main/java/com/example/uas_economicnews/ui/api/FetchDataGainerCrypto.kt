package com.example.uas_economicnews.ui.api

import com.example.uas_economicnews.ui.response.GainerResponse
import retrofit2.Response
import retrofit2.http.GET


interface FetchDataGainerCrypto {
    @GET("/query?function=TOP_GAINERS_LOSERS&apikey=G84SPBAUBEKUC5TE")
    suspend fun getData(): Response<GainerResponse>
}
