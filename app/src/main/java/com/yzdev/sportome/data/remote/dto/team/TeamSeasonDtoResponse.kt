package com.yzdev.sportome.data.remote.dto.team

import com.google.gson.annotations.SerializedName

data class TeamSeasonDtoResponse(
    val errors: List<Any?>?,
    @SerializedName("get") val get: String?,
    val paging: Paging?,
    val parameters: Parameters?,
    val response: List<Int>,
    val results: Int?
) {
    data class Paging(
        val current: Int?,
        val total: Int?
    )

    data class Parameters(
        val team: String?
    )
}