package com.yzdev.sportome.domain.model

data class MatchLeagueResponse(
    val nameLeague: String,
    val listMatch: List<MatchesResponseLocal>
)
