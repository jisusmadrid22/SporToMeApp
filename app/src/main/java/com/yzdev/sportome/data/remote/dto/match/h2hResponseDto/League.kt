package com.yzdev.sportome.data.remote.dto.match.h2hResponseDto

data class League(
    val country: String,
    val flag: String,
    val id: Int,
    val logo: String,
    val name: String,
    val round: String,
    val season: Int
)