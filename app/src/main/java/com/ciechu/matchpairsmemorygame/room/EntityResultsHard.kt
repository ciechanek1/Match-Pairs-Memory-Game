package com.ciechu.matchpairsmemorygame.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_hard")
data class EntityResultsHard(
    var score: Int
) {
    @PrimaryKey(autoGenerate = true)// mozliwe ze niepotrzebne
    var scoreId: Int = 0
}