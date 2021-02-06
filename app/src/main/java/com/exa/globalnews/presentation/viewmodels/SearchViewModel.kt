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

class SearchViewModel(
    private val repository: NewsRepository,
    private val articleDao: ArticleDao
) : ViewModel() {

    private val _searchNewsData: MutableLiveData<List<Article>> = MutableLiveData()
    val searchNewsData: LiveData<List<Article>>
        get() = _searchNewsData

    fun getSearchNews(query: String) {
        viewModelScope.launch {
            when (val resultResponse = repository.getSearchNews(query)) {
                is ResultWrapper.Success -> {
                    val latestNews = resultResponse.data
                    _searchNewsData.postValue(latestNews.articles)
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