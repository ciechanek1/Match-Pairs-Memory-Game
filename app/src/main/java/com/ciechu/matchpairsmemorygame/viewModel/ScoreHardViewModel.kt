package com.ciechu.matchpairsmemorygame.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ciechu.matchpairsmemorygame.room.EntityResultsHard
import com.ciechu.matchpairsmemorygame.room.ResultsHardRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class ScoreHardViewModel(application: Application) : AndroidViewModel(application) {

    private var resultsHardRepository: ResultsHardRepository =
        ResultsHardRepository(application)

    private var allScore: Deferred<LiveData<List<EntityResultsHard>>> =
        resultsHardRepository.getAllScore()

    fun insertScore(score: EntityResultsHard) {
        resultsHardRepository.insertScore(score)
    }

    fun getAllScore(): LiveData<List<EntityResultsHard>> = runBlocking {
        allScore.await()
    }

    fun deleteAllRows() {
        resultsHardRepository.deleteAllRows()
    }
}