package com.yzdev.sportome.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.data.remote.dto.player.AllSeasonPlayerDto

@Entity
data class LocalSeasonPlayer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val timeRequest: Long,
    val year: Long
)

/** mapper*/
fun AllSeasonPlayerDto.toLocalSeasonPlayer(): List<LocalSeasonPlayer>{
    return this.response.map {
        LocalSeasonPlayer(
            timeRequest = timeToUnix(),
            year = (it ?: 0).toLong()
        )
    }
}