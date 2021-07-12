package com.srgnk.news.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GoogleNewsAPI {

    @GET("v2/everything?" +
            "q=technology" +
            "&apiKey=fccffbac0a4149f4834d2eb29f5f3938")
    fun getNews(): Deferred<GoogleNewsResponse>
}