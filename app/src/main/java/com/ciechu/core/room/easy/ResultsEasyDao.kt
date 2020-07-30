package com.ciechu.core.room.easy

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultsEasyDao {

    @Insert
    fun insert(score: EntityResultsEasy)

    @Query("SELECT * FROM table_easy")
    fun getAllScore(): LiveData<List<EntityResultsEasy>>

    @Query("DELETE FROM table_easy")
    fun deleteAllRows()
}