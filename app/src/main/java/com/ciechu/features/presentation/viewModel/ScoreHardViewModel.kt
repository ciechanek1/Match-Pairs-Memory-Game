package com.ciechu.features.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciechu.core.room.hard.EntityResultsHard
import com.ciechu.features.data.repository.ResultsHardRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class ScoreHardViewModel(private val repository: ResultsHardRepository) : ViewModel() {

    val points by lazy {
        MutableLiveData<Int>().apply { postValue(0) }
    }

    private var allScore: Deferred<LiveData<List<EntityResultsHard>>> =
        repository.getAllScore()

    fun insertScore(score: EntityResultsHard) {
        repository.insertScore(score)
    }

    fun getAllScore(): LiveData<List<EntityResultsHard>> = runBlocking {
        allScore.await()
    }

    fun deleteAllRows() {
        repository.deleteAllRows()
    }
}