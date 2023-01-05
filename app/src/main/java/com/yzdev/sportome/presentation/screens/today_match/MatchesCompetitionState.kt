package com.yzdev.sportome.presentation.screens.today_match

import com.yzdev.sportome.domain.model.LocalMatch
import com.yzdev.sportome.domain.model.MatchLeagueResponse

data class MatchesCompetitionState(
    val isLoading: Boolean = false,
    val info: List<MatchLeagueResponse>? = null,
    val error: String = ""
)
