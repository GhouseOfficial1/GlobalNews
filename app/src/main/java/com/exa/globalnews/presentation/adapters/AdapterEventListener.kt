package com.exa.globalnews.presentation.adapters

import com.exa.globalnews.models.Article

interface AdapterEventListener {

    fun onNewsClickListener(article: Article)

    fun onNewsLikeListener(position:Int,article: Article)
}