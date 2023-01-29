package com.yzdev.sportome.data.remote.dto.match.predictions

import com.google.gson.annotations.SerializedName

data class PredictionsResponseDto(
    val errors: List<Any?>?,
    @SerializedName("get") val get: String?,
    val paging: Paging?,
    val parameters: Parameters?,
    val response: List<Response?>?,
    val results: Int?
)

data class Paging(
    val current: Int?,
    val total: Int?
)

data class Parameters(
    val fixture: String?
)

data class Response(
    val comparison: Comparison?,
    val h2h: List<H2h?>?,
    val league: League?,
    val predictions: Predictions?,
    val teams: Teams?
)