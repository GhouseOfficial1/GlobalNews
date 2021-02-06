package com.exa.globalnews.data.local

import androidx.room.*
import com.exa.globalnews.models.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * from `newsArticle`")
    suspend fun getLocalArticles(): List<Article>

    @Delete
    suspend fun deleteArticle(article: Article)
}