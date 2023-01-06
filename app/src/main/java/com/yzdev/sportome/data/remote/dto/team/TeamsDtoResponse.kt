package com.yzdev.sportome.data.remote.dto.team

import com.google.gson.annotations.SerializedName

data class TeamsDtoResponse(
    val errors: List<Any>,
    @SerializedName("get") val get: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
) {
    data class Paging(
        val current: Int,
        val total: Int
    )

    data class Parameters(
        val league: String,
        val season: String
    )

    data class Response(
        val team: Team,
        val venue: Venue
    ) {
        data class Team(
            val code: String,
            val country: String,
            val founded: Int,
            val id: Int,
            val logo: String,
            val name: String,
            val national: Boolean
        )

        data class Venue(
            val address: String,
            val capacity: Int,
            val city: String,
            val id: Int,
            val image: String,
            val name: String,
            val surface: String
        )
    }
}