package com.srgnk.news.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgnk.news.NewsState
import com.srgnk.news.api.GoogleNewsResponse
import com.srgnk.news.api.NewsApi
import com.srgnk.news.data.GoogleNews
import com.srgnk.news.data.GoogleNewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val api: NewsApi) : ViewModel() {

    val newsState: MutableLiveData<NewsState> = MutableLiveData()

    fun fetchNews() {
        viewModelScope.launch {
            try {
                val result = api.getNews().await()
                val news = process(result)
                newsState.postValue(NewsState.Success(news))
            } catch (e: Throwable) {
                newsState.postValue(NewsState.Error(e.message))
            }
        }
    }

    private fun process(response: GoogleNewsResponse): GoogleNews {
        val news = response.articles.map {
            GoogleNewsItem(
                it.title,
                it.description,
                it.urlToImage,
                it.url,
                it.content
            )
        }
        return GoogleNews(
            response.status,
            response.totalResults,
            news
        )
    }
}