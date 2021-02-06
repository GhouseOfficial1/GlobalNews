package com.exa.globalnews.di

import android.content.Context
import androidx.room.Room
import com.exa.globalnews.R
import com.exa.globalnews.data.local.AppDatabase
import com.exa.globalnews.data.local.ArticleDao
import org.koin.dsl.module

object PersistenceModule {
    val persistenceModule = module {
      single { provideAppDatabase(get()) }

      single { provideArticlesDao(get()) }
    }

    private fun provideArticlesDao(appDatabase: AppDatabase): ArticleDao {
        return appDatabase.articleDao()
    }

    private fun provideAppDatabase(context: Context):AppDatabase{
        return Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,
            context.applicationContext.getString(R.string.app_database_name)).build()
    }
}




