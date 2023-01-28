package com.yzdev.sportome.data.remote.dto.match.h2hResponseDto

data class Response(
    val fixture: Fixture,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams
)