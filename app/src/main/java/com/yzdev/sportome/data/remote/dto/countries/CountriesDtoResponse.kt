package com.yzdev.sportome.data.remote.dto.countries

import com.google.gson.annotations.SerializedName

data class CountriesDtoResponse(
    val errors: List<Any>,
    @SerializedName("get") val get: String,
    val paging: Paging,
    val parameters: List<Any>,
    val response: List<Response>,
    val results: Int
) {
    data class Paging(
        val current: Int,
        val total: Int
    )

    data class Response(
        val code: String?,
        val flag: String?,
        val name: String
    )
}