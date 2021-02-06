package com.exa.globalnews

import android.app.Application
import com.exa.globalnews.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class GlobalNewsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initKoinDi()
    }

    private fun initKoinDi() {
        startKoin {
            androidContext(this@GlobalNewsApp)
            modules(NetModule.networkModule)
            modules(PersistenceModule.persistenceModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}