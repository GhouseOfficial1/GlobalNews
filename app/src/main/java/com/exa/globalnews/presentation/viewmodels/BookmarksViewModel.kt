package com.exa.globalnews.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exa.globalnews.data.local.ArticleDao
import com.exa.globalnews.models.Article
import com.google.gson.Gson
import kotlinx.coroutines.launch
import timber.log.Timber

class BookmarksViewModel(private val articleDao: ArticleDao) : ViewModel() {

    private val bookmarkNews: MutableLiveData<List<Article>> = MutableLiveData()
    val bookmarkNewsLive: LiveData<List<Article>>
        get()  = bookmarkNews

    init {
        loadBookmarkNews()
    }

    private fun loadBookmarkNews() {
        viewModelScope.launch {
            val newsData=articleDao.getLocalArticles()
            Timber.e("DATA FROM DB ${Gson().toJson(newsData)}")
            bookmarkNews.postValue(newsData)
        }
    }

    fun deleteArticle(article: Article){
        viewModelScope.launch {
            articleDao.deleteArticle(article)
        }
    }
}