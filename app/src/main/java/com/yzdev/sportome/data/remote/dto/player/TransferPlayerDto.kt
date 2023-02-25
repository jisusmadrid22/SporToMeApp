package com.yzdev.sportome.data.remote.dto.player

import com.google.gson.annotations.SerializedName

data class TransferPlayerDto(
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
        val player: String?
    )

    data class Response(
        val player: Player,
        val transfers: List<Transfer>,
        val update: String
    ) {
        data class Player(
            val id: Int,
            val name: String
        )

        data class Transfer(
            val date: String,
            val teams: Teams,
            val type: String
        ) {
            data class Teams(
                @SerializedName("in") val inTeam: In,
                @SerializedName("out") val outTeam: Out
            ) {
                data class In(
                    val id: Int,
                    val logo: String,
                    val name: String
                )

                data class Out(
                    val id: Int,
                    val logo: String,
                    val name: String
                )
            }
        }
    }
}