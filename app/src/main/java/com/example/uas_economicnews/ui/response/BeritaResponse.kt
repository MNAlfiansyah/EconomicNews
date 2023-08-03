package com.example.uas_economicnews.ui.response

data class BeritaResponse(
    val items: Int?= null,
    val sentiment_score_definition: String?= null,
    val relevance_score_definition: String?= null,
    val feed: List<Berita>?= null,
//    val top_losers: List<topGainers>?= null,
//    val most_actively_traded: List<topGainers>?= null,
) {
    data class Berita(
        val title: String? = null,
        val url: String? = null,
        val time_published: String? = null,
        val summary: String? = null,
        val banner_image: String? = null,
        val source: String? = null,
        val overall_sentiment_score: Float? = null,
        val overall_sentiment_label: String? = null,
    )
}
