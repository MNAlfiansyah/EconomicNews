package com.example.uas_economicnews.ui.api

import com.example.uas_economicnews.ui.response.BeritaResponse
import retrofit2.Response
import retrofit2.http.GET

interface FetchDataBeritaStock {
    @GET("/query?function=NEWS_SENTIMENT&sort=LATEST&limit=30&tickers=ibm&apikey=C7U1Y6514YQVQFNT")
    suspend fun getData(): Response<BeritaResponse>
}