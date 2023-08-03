package com.example.uas_economicnews.ui.api

import com.example.uas_economicnews.ui.response.BeritaResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchDataBeritaForex {
    @GET("/query?function=NEWS_SENTIMENT&sort=LATEST&limit=30&tickers=FOREX:USD&apikey=U7NDT51U0HP34W9H")
    suspend fun getData(): Response<BeritaResponse>
}