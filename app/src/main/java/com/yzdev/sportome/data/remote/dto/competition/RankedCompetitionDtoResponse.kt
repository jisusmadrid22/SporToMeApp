package com.yzdev.sportome.data.remote.dto.competition

import com.google.gson.annotations.SerializedName

data class RankedCompetitionDtoResponse(
    val errors: List<Any?>?,
    @SerializedName("get") val get: String?,
    val paging: Paging?,
    val parameters: Parameters?,
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
        val league: League
    ) {
        data class League(
            val country: String,
            val flag: String?,
            val id: Int,
            val logo: String?,
            val name: String,
            val season: Int,
            val standings: List<List<Standing>>
        ) {
            data class Standing(
                val all: All,
                val away: Away,
                val description: String?,
                val form: String?,
                val goalsDiff: Int?,
                val group: String?,
                val home: Home,
                val points: Int?,
                val rank: Int?,
                val status: String?,
                val team: Team,
                val update: String?
            ) {
                data class All(
                    val draw: Int?,
                    val goals: Goals,
                    val lose: Int?,
                    val played: Int?,
                    val win: Int?
                ) {
                    data class Goals(
                        val against: Int?,
                        @SerializedName("for") val forTeam: Int?
                    )
                }

                data class Away(
                    val draw: Int?,
                    val goals: Goals,
                    val lose: Int?,
                    val played: Int?,
                    val win: Int?
                ) {
                    data class Goals(
                        val against: Int?,
                        @SerializedName("for") val forTeam: Int?
                    )
                }

                data class Home(
                    val draw: Int?,
                    val goals: Goals,
                    val lose: Int?,
                    val played: Int?,
                    val win: Int?
                ) {
                    data class Goals(
                        val against: Int?,
                        @SerializedName("for") val forTeam: Int?
                    )
                }

                data class Team(
                    val id: Int?,
                    val logo: String?,
                    val name: String?
                )
            }
        }
    }
}