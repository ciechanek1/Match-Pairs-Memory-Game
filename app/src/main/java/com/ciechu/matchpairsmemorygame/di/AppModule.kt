package com.ciechu.matchpairsmemorygame.di

import com.ciechu.matchpairsmemorygame.viewModel.ScoreEasyViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ScoreEasyViewModel(get()) }
}