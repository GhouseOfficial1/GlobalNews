package com.exa.globalnews.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.exa.globalnews.databinding.ActivityMainBinding
import com.exa.globalnews.models.Article
import com.exa.globalnews.presentation.adapters.AdapterEventListener
import com.exa.globalnews.presentation.adapters.NewsAdapter
import com.exa.globalnews.presentation.viewmodels.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initialize()
        observeData()
        getNewsArticles()
    }

    private fun initialize() {
        newsAdapter = NewsAdapter(getAdapterClickListener())
        binding.apply {
            recycler.apply {
                isNestedScrollingEnabled = false
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = newsAdapter
            }
            ivSearch.setOnClickListener {
                startActivity(SearchActivity.getStartIntent(this@MainActivity))
            }
            ivBookmarks.setOnClickListener {
                startActivity(BookmarksActivity.getStartIntent(this@MainActivity))
            }
        }
    }

    private fun observeData() {
        mainViewModel.apply {
            topNewsData.observe(this@MainActivity, Observer {
                it?.firstOrNull()?.let { topNews ->
                    setTopNews(topNews)
                }
            })
            popularNewsData.observe(this@MainActivity, Observer {
                newsAdapter.setArticles(it)
            })
        }
    }

    private fun setTopNews(article: Article) {
        binding.layoutTopNews.apply {
            Glide.with(ivPosterArticle).load(article.urlToImage).into(ivPosterArticle)
            tvTitleArticle.text = article.title
            tvDescArticle.text = article.description
            tvSourceArticle.text = article.source?.name
            ivBookmarkArticle.setOnClickListener {
                saveArticle(article)
            }
        }
    }

    private fun getAdapterClickListener(): AdapterEventListener {
        return object : AdapterEventListener {
            override fun onNewsClickListener(article: Article) {
                startActivity(
                    NewsDetailsActivity.getStartIntent(
                        activity = this@MainActivity,
                        articleUrl = article.url ?: ""
                    )
                )
            }

            override fun onNewsLikeListener(position: Int, article: Article) {
                saveArticle(article)
            }
        }
    }

    private fun saveArticle(article: Article) {
        article.isBookmark = true
        mainViewModel.saveBookmark(article)
    }

    private fun getNewsArticles() {
        mainViewModel.getTopNews()
        mainViewModel.getPopularNews()
    }
}

//f9cab5f928374e978349403755129614