
package com.exa.globalnews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exa.globalnews.models.Article
import com.exa.globalnews.models.SourcesConverter

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(SourcesConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
}