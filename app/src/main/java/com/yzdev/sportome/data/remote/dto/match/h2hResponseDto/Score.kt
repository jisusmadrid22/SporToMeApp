package com.yzdev.sportome.data.remote.dto.match.h2hResponseDto

data class Score(
    val extratime: Extratime,
    val fulltime: Fulltime,
    val halftime: Halftime,
    val penalty: Penalty
)