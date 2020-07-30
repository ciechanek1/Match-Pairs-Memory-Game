package com.ciechu.core.room.hard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultsHardDao {

    @Insert
    fun insert(score: EntityResultsHard)

    @Query("SELECT * FROM table_hard")
    fun getAllScore(): LiveData<List<EntityResultsHard>>

    @Query("DELETE FROM table_hard")
    fun deleteAllRows()
}