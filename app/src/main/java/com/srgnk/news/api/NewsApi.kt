package com.srgnk.news.api

import kotlinx.coroutines.Deferred

interface NewsApi {

    suspend fun getNews(): Deferred<GoogleNewsResponse>
}