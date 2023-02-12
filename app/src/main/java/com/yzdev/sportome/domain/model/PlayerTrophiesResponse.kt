package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.player.PlayerTrophiesDto

data class PlayerTrophiesResponse(
    val response: List<Response>
) {
    data class Response(
        val country: String,
        val league: String,
        val place: String,
        val season: String
    )
}

/** mapper*/
fun PlayerTrophiesDto.toPlayerTrophiesLocal(): PlayerTrophiesResponse{
    return PlayerTrophiesResponse(
        response = response.toPlayerTrophiesLocalResponse()
    )
}

private fun List<PlayerTrophiesDto.Response>.toPlayerTrophiesLocalResponse(): List<PlayerTrophiesResponse.Response>{
    return this.map {
        PlayerTrophiesResponse.Response(
            country = it.country,
            league = it.league,
            place = it.place,
            season = it.season
        )
    }
}