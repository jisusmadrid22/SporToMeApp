package com.yzdev.sportome.data.remote.dto.competition

import com.google.gson.annotations.SerializedName

data class SeasonsDtoResponse(
    val errors: List<Any>,
    @SerializedName("get") val get: String,
    val paging: Paging,
    val parameters: List<Any>,
    val response: List<Int>,
    val results: Int
) {
    data class Paging(
        val current: Int,
        val total: Int
    )
}