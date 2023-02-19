package com.yzdev.sportome.data.remote.dto.team

import com.google.gson.annotations.SerializedName

data class TeamStatsDtoResponse(
    val errors: List<Any?>?,
    @SerializedName("get") val get: String?,
    val paging: Paging?,
    val parameters: Parameters?,
    val response: Response,
    val results: Int?
) {
    data class Paging(
        val current: Int?,
        val total: Int?
    )

    data class Parameters(
        val league: String?,
        val season: String?,
        val team: String?
    )

    data class Response(
        val clean_sheet: CleanSheet,
        val failed_to_score: FailedToScore,
        val fixtures: Fixtures,
        val form: String?,
        val goals: Goals,
        val league: League,
        val lineups: List<Lineup>,
        val penalty: Penalty,
        val team: Team
    ) {
        data class CleanSheet(
            val away: Int?,
            val home: Int?,
            val total: Int?
        )

        data class FailedToScore(
            val away: Int?,
            val home: Int?,
            val total: Int?
        )

        data class Fixtures(
            val draws: Draws,
            val loses: Loses,
            val played: Played,
            val wins: Wins
        ) {
            data class Draws(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )

            data class Loses(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )

            data class Played(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )

            data class Wins(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )
        }

        data class Goals(
            val against: Against,
            @SerializedName("for") val forTeam: For
        ) {
            data class Against(
                val total: Total
            ) {
                data class Total(
                    val away: Int?,
                    val home: Int?,
                    val total: Int?
                )
            }

            data class For(
                val total: Total
            ) {
                data class Total(
                    val away: Int?,
                    val home: Int?,
                    val total: Int?
                )
            }
        }

        data class League(
            val country: String?,
            val flag: String?,
            val id: Int?,
            val logo: String?,
            val name: String?,
            val season: Int?
        )

        data class Lineup(
            val formation: String?,
            val played: Int?
        )

        data class Penalty(
            val missed: Missed,
            val scored: Scored,
            val total: Int?
        ) {
            data class Missed(
                val percentage: String?,
                val total: Int?
            )

            data class Scored(
                val percentage: String?,
                val total: Int?
            )
        }

        data class Team(
            val id: Int?,
            val logo: String?,
            val name: String?
        )
    }
}