package com.ciechu.matchpairsmemorygame.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [EntityResultsEasy::class], version = 1)
abstract class ScoreDatabase : RoomDatabase() {

    abstract fun resultsEasyDao(): ResultsEasyDao

    companion object {
        private val DATABASE_NAME = "userdb.db"
        private var INSTANCE: ScoreDatabase? = null

        fun getScoreDataBase(context: Context): ScoreDatabase {
            val temporaryInstance =
                INSTANCE
            if (temporaryInstance != null) {
                return temporaryInstance
            }

            synchronized(ScoreDatabase::class) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    ScoreDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}