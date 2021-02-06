package com.exa.globalnews.presentation.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.exa.globalnews.databinding.ActivityNewsDetailsBinding

class NewsDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initialize()
        loadDetails()
    }

    private fun initialize() {
        binding.apply {
            ivBack.setOnClickListener { finish() }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun loadDetails() {
        val url = intent?.getStringExtra(ARTICLE_URL) ?: return
        binding.tvUrlArticle.text = url
        binding.webView.apply {
            settings.apply {
                domStorageEnabled = true
                blockNetworkImage = false
                blockNetworkLoads = false
                javaScriptEnabled = true
                javaScriptCanOpenWindowsAutomatically = false
            }
            loadUrl(url)
        }
    }


    companion object {
        const val ARTICLE_URL = "ARTICLE_URL"
        fun getStartIntent(activity: AppCompatActivity, articleUrl: String): Intent {
            return Intent(activity, NewsDetailsActivity::class.java).apply {
                putExtra(ARTICLE_URL, articleUrl)
            }
        }
    }
}