package com.yzdev.sportome.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yzdev.sportome.data.remote.dto.team.TeamsDtoResponse

@Entity
data class LocalTeam(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idApi: Int,
    val code: String?,
    val country: String?,
    val logo: String?,
    val name: String,
)

fun TeamsDtoResponse.toListLocalTeam(): List<LocalTeam>{
    return this.response.map {
        LocalTeam(
            idApi = it.team.id,
            code = it.team.code,
            country = it.team.country,
            logo = it.team.logo,
            name = it.team.name
        )
    }
}
