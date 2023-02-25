package com.yzdev.sportome.data.remote.dto.player

import com.google.gson.annotations.SerializedName

data class PlayerTrophiesDto(
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
        val country: String,
        val league: String,
        val place: String,
        val season: String
    )
}