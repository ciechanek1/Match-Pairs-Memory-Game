package com.ciechu.matchpairsmemorygame.room

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class ResultsEasyRepository(application: Application) {

    private var resultsEasyDao: ResultsEasyDao

    init {
        val database = ScoreDatabase.getScoreDataBase(application.applicationContext)

        resultsEasyDao = database.resultsEasyDao()
    }

    fun insertScore(score: EntityResultsEasy) = CoroutineScope(Dispatchers.IO).launch {
        resultsEasyDao.insert(score)
    }

    fun getAllScore(): Deferred<LiveData<List<EntityResultsEasy>>> =
        CoroutineScope(Dispatchers.IO).async {
            resultsEasyDao.getAllScore()
        }

    fun deleteAllRows() = CoroutineScope(Dispatchers.IO).launch {
        resultsEasyDao.deleteAllRows()
    }
}