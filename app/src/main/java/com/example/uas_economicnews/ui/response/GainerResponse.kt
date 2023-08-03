package com.example.uas_economicnews.ui.response

data class GainerResponse (
    val metadata: String?= null,
    val last_updated: String?= null,
    val top_gainers: List<topGainers>?= null,
//    val top_losers: List<topGainers>?= null,
//    val most_actively_traded: List<topGainers>?= null,
){
    data class topGainers (
        val ticker: String?= null,
        val price: Float?= null,
        val change_amount: Float?= null,
        val change_percentage: String?= null,
        val volume: Int?= null
    )

}