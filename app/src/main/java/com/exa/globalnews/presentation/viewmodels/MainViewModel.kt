package com.exa.globalnews.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exa.globalnews.data.local.ArticleDao
import com.exa.globalnews.data.remote.response.ResultWrapper
import com.exa.globalnews.data.repository.NewsRepository
import com.exa.globalnews.models.Article
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    private val repository: NewsRepository,
    private val articleDao: ArticleDao
) : ViewModel() {

    private val _topNewsData: MutableLiveData<List<Article>> = MutableLiveData()
    val topNewsData: LiveData<List<Article>>
        get() = _topNewsData

    private val _popularNewsData: MutableLiveData<List<Article>> = MutableLiveData()
    val popularNewsData: LiveData<List<Article>>
        get() = _popularNewsData

    fun getTopNews() {
        viewModelScope.launch {
            when (val resultResponse = repository.getTopNews()) {
                is ResultWrapper.Success -> {
                    val latestNews = resultResponse.data
                    _topNewsData.postValue(latestNews.articles)
                }
                is ResultWrapper.Error -> {
                    Timber.e("Error in response ${resultResponse.exception.localizedMessage}")
                }
            }
        }
    }

    fun getPopularNews() {
        viewModelScope.launch {
            when (val resultResponse = repository.getPopularNews()) {
                is ResultWrapper.Success -> {
                    val latestNews = resultResponse.data
                    _popularNewsData.postValue(latestNews.articles)
                }
                is ResultWrapper.Error -> {
                    Timber.e("Error in response ${resultResponse.exception.localizedMessage}")
                }
            }
        }
    }

    fun saveBookmark(article: Article) {
        viewModelScope.launch {
            articleDao.insertArticle(article)
        }
    }

}