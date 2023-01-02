package com.yzdev.sportome.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.data.remote.dto.competition.SeasonsDtoResponse
import java.sql.Timestamp

@Entity
data class LocalSeasons(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timeRequest: Long,
    val year: Int
)

fun SeasonsDtoResponse.toListLocalSeasons(): List<LocalSeasons>{
    return this.response.map {
        LocalSeasons(
            timeRequest = timeToUnix(),
            year = it
        )
    }
}