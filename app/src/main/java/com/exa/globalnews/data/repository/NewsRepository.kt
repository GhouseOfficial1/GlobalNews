package com.exa.globalnews.data.repository

import com.exa.globalnews.data.remote.ApiService
import com.exa.globalnews.data.remote.response.NewsResponse
import com.exa.globalnews.data.remote.response.ResultWrapper
import com.google.gson.Gson
import timber.log.Timber

class NewsRepository(private val apiService: ApiService) : Repository {

    override suspend fun getTopNews(): ResultWrapper<NewsResponse> {
        try {
            val dataResponse = apiService.loadTopHeadlines()
            var newsResponse: NewsResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    newsResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(newsResponse!!)

        } catch (exception: Exception) {

            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getPopularNews(): ResultWrapper<NewsResponse> {
        try {
            val dataResponse = apiService.loadPopularNews()
            var newsResponse: NewsResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    newsResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(newsResponse!!)

        } catch (exception: Exception) {

            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }

    override suspend fun getSearchNews(query: String): ResultWrapper<NewsResponse> {
        try {
            val dataResponse = apiService.loadSearchNews(query)
            var newsResponse: NewsResponse? = null
            when {
                dataResponse.isSuccessful -> {
                    Timber.e("SUCCESS RESP ${Gson().toJson(dataResponse.body())}")
                    newsResponse = dataResponse.body()!!
                }
                else -> {
                    Timber.e("ERROR RESP ${Gson().toJson(dataResponse.errorBody())}")
                }
            }
            return ResultWrapper.Success(newsResponse!!)

        } catch (exception: Exception) {
            val errorResponse = ResultWrapper.Error(exception)
            Timber.e("ERROR RESP ${Gson().toJson(errorResponse.exception.localizedMessage)}")
            return ResultWrapper.Error(errorResponse.exception)
        }
    }
}