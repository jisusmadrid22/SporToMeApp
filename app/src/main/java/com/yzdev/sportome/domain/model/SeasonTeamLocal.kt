package com.yzdev.sportome.domain.model

import com.yzdev.sportome.data.remote.dto.team.TeamSeasonDtoResponse

data class TeamSeasonLocalResponse(
    val listSeason: List<Int>
)

fun TeamSeasonDtoResponse.toListLocalSeasons(): TeamSeasonLocalResponse{
    return TeamSeasonLocalResponse(
        listSeason = response
    )
}