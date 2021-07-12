package com.srgnk.news.api


class GoogleNewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticlesResponse>
)

class ArticlesResponse(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)