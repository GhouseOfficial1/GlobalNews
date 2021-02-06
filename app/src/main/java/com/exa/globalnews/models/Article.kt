package com.exa.globalnews.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.exa.globalnews.data.remote.response.Source

@Entity(tableName = "newsArticle")
data class Article(
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("description")
    var description: String?,
    @PrimaryKey
    @SerializedName("publishedAt")
    var publishedAt: String,
    @SerializedName("source")
    var source: Source?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?,
    var isBookmark: Boolean = false
)