package com.yzdev.sportome.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse

@Entity
data class LocalCompetition(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val idApi: Int,
    val logo: String,
    val name: String,
    val type: String,
    val countryCode: String?,
    val countryFlag: String?,
    val countryName: String,
    val yearSeason: Int
)

fun CompetitionDtoResponse.toListLocalCompetition(): List<LocalCompetition>{
    return this.response.map {
        LocalCompetition(
            idApi = it.league.id,
            logo = it.league.logo,
            name = it.league.name,
            type = it.league.type,
            countryCode = it.country.code,
            countryFlag = it.country.flag,
            countryName = it.country.name,
            yearSeason = it.seasons.first().year
        )
    }
}
