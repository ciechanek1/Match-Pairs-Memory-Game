package com.ciechu.matchpairsmemorygame.room

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class ResultsHardRepository(application: Application) {

    private var resultsHardDao: ResultsHardDao

    init {
        val database = ScoreDatabase.getScoreDataBase(application.applicationContext)

        resultsHardDao = database.resultsHardDao()
    }

    fun insertScore(score: EntityResultsHard) = CoroutineScope(Dispatchers.IO).launch {
        resultsHardDao.insert(score)
    }

    fun getAllScore(): Deferred<LiveData<List<EntityResultsHard>>> =
        CoroutineScope(Dispatchers.IO).async {
            resultsHardDao.getAllScore()
        }

    fun deleteAllRows() = CoroutineScope(Dispatchers.IO).launch {
        resultsHardDao.deleteAllRows()
    }
}