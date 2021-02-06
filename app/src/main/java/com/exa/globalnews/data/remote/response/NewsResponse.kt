package com.exa.globalnews.data.remote.response

import com.exa.globalnews.models.Article
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    var articles: List<Article>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("totalResults")
    var totalResults: Int?
)