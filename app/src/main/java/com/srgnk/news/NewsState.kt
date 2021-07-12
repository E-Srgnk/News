package com.srgnk.news

import com.srgnk.news.data.GoogleNews

sealed class NewsState {
    class Success(val googleNews: GoogleNews): NewsState()
    class Error(val message: String?) : NewsState()
}