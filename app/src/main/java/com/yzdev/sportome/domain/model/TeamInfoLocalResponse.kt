package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.team.TeamInfoDtoResponse

data class TeamInfoLocalResponse(
    val response: List<Response>,
) {
    data class Response(
        val team: Team,
        val venue: Venue
    ) {
        data class Team(
            val code: String?,
            val country: String?,
            val founded: Int?,
            val id: Int?,
            val logo: String?,
            val name: String?,
            val national: Boolean?
        )

        data class Venue(
            val address: String?,
            val capacity: Int?,
            val city: String?,
            val id: Int?,
            val image: String?,
            val name: String?,
            val surface: String?
        )
    }
}

/** mapper*/

fun TeamInfoDtoResponse.toTeamInfoLocal(): TeamInfoLocalResponse{
    return TeamInfoLocalResponse(
        response = response.toTeamInfoLocalResponse()
    )
}

private fun List<TeamInfoDtoResponse.Response>.toTeamInfoLocalResponse(): List<TeamInfoLocalResponse.Response>{
    return this.map {
        TeamInfoLocalResponse.Response(
            team = it.team.toTeamInfoLocalResponseTeam(),
            venue = it.venue.toTeamInfoLocalResponseVenue()
        )
    }
}

fun TeamInfoDtoResponse.Response.Team.toTeamInfoLocalResponseTeam(): TeamInfoLocalResponse.Response.Team{
    return TeamInfoLocalResponse.Response.Team(
        code, country, founded, id, logo, name, national
    )
}

fun TeamInfoDtoResponse.Response.Venue.toTeamInfoLocalResponseVenue(): TeamInfoLocalResponse.Response.Venue{
    return TeamInfoLocalResponse.Response.Venue(
        address, capacity, city, id, image, name, surface
    )
}