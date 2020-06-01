package com.ericho.example

import com.ericho.example.ui.main.MainViewModel
import com.ericho.example.ui.novel.NovelViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // view model
    viewModel<MainViewModel>()
    viewModel<NovelViewModel>()
}