package com.ciechu.features.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciechu.core.room.easy.EntityResultsEasy
import com.ciechu.features.data.repository.ResultsEasyRepository

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking

class ScoreEasyViewModel(private val repository: ResultsEasyRepository) : ViewModel() {

    val points by lazy {
        MutableLiveData<Int>().apply { postValue(0) }
    }

    private val allScore: Deferred<LiveData<List<EntityResultsEasy>>> =
        repository.getAllScore()

    fun insert(score: EntityResultsEasy) {
        repository.insertScore(score)
    }

    fun getAllScore(): LiveData<List<EntityResultsEasy>> = runBlocking {
        allScore.await()
    }

    fun deleteAllRows() {
        repository.deleteAllRows()
    }
}