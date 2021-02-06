package com.exa.globalnews.data.remote

import com.exa.globalnews.BuildConfig
import com.exa.globalnews.constant.categoryPopularNews
import com.exa.globalnews.constant.defaultCountryCode
import com.exa.globalnews.constant.pageSizePopularNews
import com.exa.globalnews.constant.pageSizeTopNews
import com.exa.globalnews.data.remote.response.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v2/top-headlines")
    suspend fun loadTopHeadlines(
        @Query("country") countryCode: String = defaultCountryCode,
        @Query("pageSize") pageSize: Int = pageSizeTopNews,
        @Query("apiKey") apiKey :String = BuildConfig.NEWS_API_KEY
    ): Response<NewsResponse>

    @GET("/v2/top-headlines")
    suspend fun loadPopularNews(
        @Query("country") countryCode: String = defaultCountryCode,
        @Query("category") category: String = categoryPopularNews,
        @Query("pageSize") pageSize: Int = pageSizePopularNews,
        @Query("apiKey") apiKey :String = BuildConfig.NEWS_API_KEY
    ): Response<NewsResponse>

    @GET("/v2/top-headlines")
    suspend fun loadSearchNews(
        @Query("q") query: String,
        @Query("country") countryCode: String = defaultCountryCode,
        @Query("pageSize") pageSize: Int = pageSizePopularNews,
        @Query("apiKey") apiKey :String = BuildConfig.NEWS_API_KEY
    ): Response<NewsResponse>

}
