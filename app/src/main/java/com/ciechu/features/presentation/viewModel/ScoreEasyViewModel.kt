package com.ciechu.features.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ciechu.core.room.easy.EntityResultsEasy
import com.ciechu.features.data.repository.ResultsEasyRepository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class ScoreEasyViewModel(application: Application) : AndroidViewModel(application) {

    private val resultsEasyRepository: ResultsEasyRepository =
        ResultsEasyRepository(
            application
        )

    private val allScore: Deferred<LiveData<List<EntityResultsEasy>>> =
        resultsEasyRepository.getAllScore()

    fun insert(score: EntityResultsEasy) {
        resultsEasyRepository.insertScore(score)
    }

    fun getAllScore(): LiveData<List<EntityResultsEasy>> = runBlocking {
        allScore.await()
    }

    fun deleteAllRows() {
        resultsEasyRepository.deleteAllRows()
    }
}