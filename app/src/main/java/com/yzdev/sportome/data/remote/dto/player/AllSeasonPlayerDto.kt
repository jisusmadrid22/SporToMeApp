package com.yzdev.sportome.data.remote.dto.player

import com.google.gson.annotations.SerializedName

data class AllSeasonPlayerDto(
    val errors: List<Any?>?,
    @SerializedName("get") val get: String?,
    val paging: Paging?,
    val parameters: List<Any?>?,
    val response: List<Int?>,
    val results: Int?
)