package com.exa.globalnews.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exa.globalnews.databinding.ActivityBookmarksBinding
import com.exa.globalnews.models.Article
import com.exa.globalnews.presentation.adapters.AdapterEventListener
import com.exa.globalnews.presentation.adapters.NewsAdapter
import com.exa.globalnews.presentation.viewmodels.BookmarksViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class BookmarksActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarksBinding
    private val bookmarksViewModel: BookmarksViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarksBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initialize()
        observeData()
    }

    private fun initialize() {
        newsAdapter = NewsAdapter(getAdapterClickListener())
        binding.apply {
            recycler.apply {
                layoutManager = LinearLayoutManager(this@BookmarksActivity)
                adapter = newsAdapter
            }
            ivBack.setOnClickListener { finish() }
            ivSearch.setOnClickListener {
                startActivity(SearchActivity.getStartIntent(this@BookmarksActivity))
            }
        }
    }

    private fun observeData() {
        bookmarksViewModel.apply {
            bookmarkNewsLive.observe(this@BookmarksActivity, Observer {
                newsAdapter.setArticles(it)
            })
        }
    }

    private fun getAdapterClickListener(): AdapterEventListener {
        return object : AdapterEventListener {
            override fun onNewsClickListener(article: Article) {
                startActivity(
                    NewsDetailsActivity.getStartIntent(
                        activity = this@BookmarksActivity,
                        articleUrl = article.url ?: ""
                    )
                )
            }

            override fun onNewsLikeListener(position: Int, article: Article) {
                article.isBookmark = false
                bookmarksViewModel.deleteArticle(article)
                newsAdapter.removeArticle(position)
            }
        }
    }

    companion object {
        fun getStartIntent(activity: AppCompatActivity): Intent {
            return Intent(activity, BookmarksActivity::class.java)
        }
    }
}