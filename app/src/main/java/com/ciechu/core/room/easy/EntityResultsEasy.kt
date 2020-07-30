package com.ciechu.core.room.easy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_easy")
data class EntityResultsEasy(
    var score: Int
) {
    @PrimaryKey(autoGenerate = true)
    var scoreId: Int = 0
}