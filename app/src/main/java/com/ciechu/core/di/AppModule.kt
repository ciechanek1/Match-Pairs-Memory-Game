package com.ciechu.core.di

import com.ciechu.features.presentation.viewModel.ScoreEasyViewModel
import com.ciechu.features.presentation.viewModel.ScoreHardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScoreEasyViewModel(get()) }
    viewModel { ScoreHardViewModel(get()) }
}