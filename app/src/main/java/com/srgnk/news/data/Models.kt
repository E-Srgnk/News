package com.srgnk.news.data

data class GoogleNews(
    val status: String,
    val totalResults: Int,
    val news: List<GoogleNewsItem>
)

data class GoogleNewsItem(
    val title: String,
    val description: String,
    val urlToImage: String,
    val url: String,
    val content: String
)