package com.ciechu.matchpairsmemorygame.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ciechu.matchpairsmemorygame.room.EntityResultsEasy
import com.ciechu.matchpairsmemorygame.room.ResultsEasyRepository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class ScoreEasyViewModel(application: Application) : AndroidViewModel(application) {

    private val resultsEasyRepository: ResultsEasyRepository = ResultsEasyRepository(application)

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