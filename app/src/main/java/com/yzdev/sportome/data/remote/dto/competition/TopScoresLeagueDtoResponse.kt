package com.yzdev.sportome.data.remote.dto.competition

import com.google.gson.annotations.SerializedName

data class TopScoresLeagueDtoResponse(
    val errors: List<Any?>?,
    @SerializedName("get") val get: String?,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int?
) {
    data class Paging(
        val current: Int?,
        val total: Int?
    )

    data class Parameters(
        val league: String?,
        val season: String?
    )

    data class Response(
        val player: Player,
        val statistics: List<Statistic>
    ) {
        data class Player(
            val age: Int?,
            val birth: Birth,
            val firstname: String?,
            val height: String?,
            val id: Int?,
            val injured: Boolean?,
            val lastname: String?,
            val name: String?,
            val nationality: String?,
            val photo: String?,
            val weight: String?
        ) {
            data class Birth(
                val country: String?,
                val date: String?,
                val place: String?
            )
        }

        data class Statistic(
            val cards: Cards,
            val dribbles: Dribbles,
            val duels: Duels,
            val fouls: Fouls,
            val games: Games,
            val goals: Goals,
            val league: League,
            val passes: Passes,
            val penalty: Penalty,
            val shots: Shots,
            val substitutes: Substitutes,
            val tackles: Tackles,
            val team: Team
        ) {
            data class Cards(
                val red: Int?,
                val yellow: Int?,
                val yellowred: Int?
            )

            data class Dribbles(
                val attempts: Int?,
                val past: Any?,
                val success: Int?
            )

            data class Duels(
                val total: Int?,
                val won: Int?
            )

            data class Fouls(
                val committed: Int?,
                val drawn: Int?
            )

            data class Games(
                val appearences: Int?,
                val captain: Boolean?,
                val lineups: Int?,
                val minutes: Int?,
                val number: Int?,
                val position: String?,
                val rating: String?
            )

            data class Goals(
                val assists: Int?,
                val conceded: Int?,
                val saves: Int?,
                val total: Int?
            )

            data class League(
                val country: String?,
                val flag: String?,
                val id: Int?,
                val logo: String?,
                val name: String?,
                val season: Int?
            )

            data class Passes(
                val accuracy: Int?,
                val key: Int?,
                val total: Int?
            )

            data class Penalty(
                val commited: Int?,
                val missed: Int?,
                val saved: Int?,
                val scored: Int?,
                val won: Int?
            )

            data class Shots(
                val on: Int?,
                val total: Int?
            )

            data class Substitutes(
                val bench: Int?,
                @SerializedName("in") val inTeam: Int?,
                @SerializedName("out") val outTeam: Int?
            )

            data class Tackles(
                val blocks: Int?,
                val interceptions: Int?,
                val total: Int?
            )

            data class Team(
                val id: Int?,
                val logo: String?,
                val name: String?
            )
        }
    }
}