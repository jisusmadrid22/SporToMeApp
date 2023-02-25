package com.yzdev.sportome.data.remote.dto.match

import com.google.gson.annotations.SerializedName

data class MatchesForThisWeekDtoResponse(
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
        val from: String,
        val league: String,
        val season: String,
        val to: String
    )

    data class Response(
        val fixture: Fixture,
        val goals: Goals,
        val league: League,
        val score: Score,
        val teams: Teams
    ) {
        data class Fixture(
            val date: String,
            val id: Int,
            val periods: Periods,
            val referee: String?,
            val status: Status,
            val timestamp: Long,
            val timezone: String,
            val venue: Venue
        ) {
            data class Periods(
                val first: Long?,
                val second: Long?
            )

            data class Status(
                val elapsed: Int?,
                val long: String,
                val short: String
            )

            data class Venue(
                val city: String,
                val id: Int,
                val name: String
            )
        }

        data class Goals(
            val away: Int?,
            val home: Int?
        )

        data class League(
            val country: String,
            val flag: String?,
            val id: Int,
            val logo: String,
            val name: String,
            val round: String,
            val season: Int
        )

        data class Score(
            val extratime: Extratime,
            val fulltime: Fulltime,
            val halftime: Halftime,
            val penalty: Penalty
        ) {
            data class Extratime(
                val away: Int?,
                val home: Int?
            )

            data class Fulltime(
                val away: Int?,
                val home: Int?
            )

            data class Halftime(
                val away: Int?,
                val home: Int?
            )

            data class Penalty(
                val away: Int?,
                val home: Int?
            )
        }

        data class Teams(
            val away: Away,
            val home: Home
        ) {
            data class Away(
                val id: Int,
                val logo: String,
                val name: String,
                val winner: Boolean?
            )

            data class Home(
                val id: Int,
                val logo: String,
                val name: String,
                val winner: Boolean?
            )
        }
    }
}