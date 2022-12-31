package com.yzdev.sportome.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalCountry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idApi: Int,
    val timeRequest: Long,
    val name: String,
    val code: String
)
