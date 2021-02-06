package com.exa.globalnews.di

import com.exa.globalnews.presentation.viewmodels.BookmarksViewModel
import com.exa.globalnews.presentation.viewmodels.MainViewModel
import com.exa.globalnews.presentation.viewmodels.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { BookmarksViewModel(get()) }
    viewModel { SearchViewModel(get(), get()) }
}