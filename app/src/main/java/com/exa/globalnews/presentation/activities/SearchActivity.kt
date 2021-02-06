package com.exa.globalnews.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exa.globalnews.databinding.ActivitySearchBinding
import com.exa.globalnews.models.Article
import com.exa.globalnews.presentation.adapters.AdapterEventListener
import com.exa.globalnews.presentation.adapters.NewsAdapter
import com.exa.globalnews.presentation.viewmodels.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initialize()
        observeData()
    }

    private fun initialize() {
        newsAdapter = NewsAdapter(getAdapterClickListener())
        binding.apply {
            recycler.apply {
                layoutManager = LinearLayoutManager(this@SearchActivity)
                adapter = newsAdapter
            }
            ivBack.setOnClickListener { finish() }
            etSearch.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch()
                    return@setOnEditorActionListener true
                }
                false
            }
        }
    }

    private fun observeData() {
        searchViewModel.apply {
            searchNewsData.observe(this@SearchActivity, Observer {
                newsAdapter.setArticles(it)
            })
        }
    }

    private fun getAdapterClickListener(): AdapterEventListener {
        return object : AdapterEventListener {
            override fun onNewsClickListener(article: Article) {
                startActivity(
                    NewsDetailsActivity.getStartIntent(
                        activity = this@SearchActivity,
                        articleUrl = article.url ?: ""
                    )
                )
            }

            override fun onNewsLikeListener(position: Int, article: Article) {
                article.isBookmark = true
                searchViewModel.saveBookmark(article)
            }
        }
    }

    private fun performSearch() {
        searchViewModel.getSearchNews(binding.etSearch.text.toString())
    }

    companion object {
        fun getStartIntent(activity: AppCompatActivity): Intent {
            return Intent(activity, SearchActivity::class.java)
        }
    }

}