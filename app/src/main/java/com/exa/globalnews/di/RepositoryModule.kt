package com.exa.globalnews.di

import com.exa.globalnews.data.repository.NewsRepository
import org.koin.dsl.module


val repositoryModule = module {
    single { NewsRepository(get()) }

}