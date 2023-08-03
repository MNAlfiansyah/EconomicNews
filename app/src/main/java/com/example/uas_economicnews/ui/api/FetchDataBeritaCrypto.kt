package com.example.uas_economicnews.ui.api

import com.example.uas_economicnews.ui.response.BeritaResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchDataBeritaCrypto {
    @GET("/query?function=NEWS_SENTIMENT&sort=LATEST&limit=30&tickers=CRYPTO:BTC&apikey=G84SPBAUBEKUC5TE")
    suspend fun getData(): Response<BeritaResponse>
}