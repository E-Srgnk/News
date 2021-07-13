package com.srgnk.news.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.srgnk.news.api.GoogleNewsAPI
import com.srgnk.news.api.NewsApi
import com.srgnk.news.api.NewsRestApi
import com.srgnk.news.commons.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNewsApi(googleNewsAPI: GoogleNewsAPI): NewsApi = NewsRestApi(googleNewsAPI)

    @Singleton
    @Provides
    fun provideGoogleAPI(retrofit: Retrofit): GoogleNewsAPI = retrofit.create(GoogleNewsAPI::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}