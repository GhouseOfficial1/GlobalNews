package com.exa.globalnews.data.repository

import com.exa.globalnews.data.remote.response.NewsResponse
import com.exa.globalnews.data.remote.response.ResultWrapper

interface Repository {

    suspend fun getTopNews(): ResultWrapper<NewsResponse>

    suspend fun getPopularNews(): ResultWrapper<NewsResponse>

    suspend fun getSearchNews(query: String): ResultWrapper<NewsResponse>
}