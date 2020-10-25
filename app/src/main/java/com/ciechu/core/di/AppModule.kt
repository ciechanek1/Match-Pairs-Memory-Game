package com.ciechu.core.di

import com.ciechu.features.data.repository.ResultsEasyRepository
import com.ciechu.features.data.repository.ResultsHardRepository
import com.ciechu.features.presentation.dialogFragment.LevelSelectionDialogFragment
import com.ciechu.features.presentation.viewModel.ScoreEasyViewModel
import com.ciechu.features.presentation.viewModel.ScoreHardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { ResultsEasyRepository(get()) }
    factory { ResultsHardRepository(get()) }

    viewModel { ScoreEasyViewModel(get()) }
    viewModel { ScoreHardViewModel(get()) }

    factory { LevelSelectionDialogFragment() }
}