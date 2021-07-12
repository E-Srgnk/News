package com.srgnk.news.api

import kotlinx.coroutines.Deferred
import javax.inject.Inject

class NewsRestApi @Inject constructor(private val googleNewsApi: GoogleNewsAPI) : NewsApi {

    override suspend fun getNews(): Deferred<GoogleNewsResponse> {
        return googleNewsApi.getNews()
    }
}