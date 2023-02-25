package com.yzdev.sportome.presentation.screens.today_match

import com.yzdev.sportome.domain.model.MatchLeagueResponse
import com.yzdev.sportome.domain.model.MatchesResponseLocal

data class MatchesTeamState(
    val isLoading: Boolean = false,
    val info: List<MatchesResponseLocal>? = null,
    val error: String = ""
)
