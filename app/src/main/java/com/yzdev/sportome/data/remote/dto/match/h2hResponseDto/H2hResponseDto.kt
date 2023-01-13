package com.yzdev.sportome.data.remote.dto.match.h2hResponseDto

import com.google.gson.annotations.SerializedName

data class H2hResponseDto(
    val errors: List<Any>,
    @SerializedName("get") val get: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
)