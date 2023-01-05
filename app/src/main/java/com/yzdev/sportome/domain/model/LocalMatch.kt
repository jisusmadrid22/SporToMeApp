package com.yzdev.sportome.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalMatch(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idMatch: Int,
    val idLeague: Int,
    val seasonYear: Int,
    val timestamp: Long
)
