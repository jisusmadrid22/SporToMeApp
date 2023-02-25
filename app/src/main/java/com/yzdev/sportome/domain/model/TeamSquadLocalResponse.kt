package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.team.TeamSquadDtoResponse

data class TeamSquadLocalResponse(
    val response: List<Response>,
) {
    data class Response(
        val players: List<Player>,
        val team: Team
    ) {
        data class Player(
            val age: Int?,
            val id: Int?,
            val name: String?,
            val number: Int?,
            val photo: String?,
            val position: String?
        )

        data class Team(
            val id: Int?,
            val logo: String?,
            val name: String?
        )
    }
}

/** mapper*/

fun TeamSquadDtoResponse.toTeamSquadLocal(): TeamSquadLocalResponse{
    return TeamSquadLocalResponse(
        response = response.toTeamSquadLocalResponse()
    )
}

private fun List<TeamSquadDtoResponse.Response>.toTeamSquadLocalResponse(): List<TeamSquadLocalResponse.Response> {
    return this.map {
        TeamSquadLocalResponse.Response(
            players = it.players.toTeamSquadLocalResponsePlayer(),
            team = it.team.toTeamSquadLocalResponseTeam()
        )
    }
}

private fun List<TeamSquadDtoResponse.Response.Player>.toTeamSquadLocalResponsePlayer(): List<TeamSquadLocalResponse.Response.Player> {
    return this.map {
        TeamSquadLocalResponse.Response.Player(
            age = it.age,
            id = it.id,
            name = it.name,
            number = it.number,
            photo = it.photo,
            position = it.position
        )
    }
}

private fun TeamSquadDtoResponse.Response.Team.toTeamSquadLocalResponseTeam(): TeamSquadLocalResponse.Response.Team {
    return TeamSquadLocalResponse.Response.Team(
        id = id,
        name = name,
        logo = logo
    )
}