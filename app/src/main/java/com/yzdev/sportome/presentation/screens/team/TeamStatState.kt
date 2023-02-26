package com.yzdev.sportome.presentation.screens.team

import com.yzdev.sportome.domain.model.TeamStatsLocalResponse

data class TeamStatState(
    val isLoading: Boolean = false,
    val info: TeamStatsLocalResponse? = null,
    val error: String = ""
)
