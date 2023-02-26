package com.yzdev.sportome.presentation.screens.team

import com.yzdev.sportome.domain.model.TeamSeasonLocalResponse

data class SeasonTeamState(
    val isLoading: Boolean = false,
    val info: TeamSeasonLocalResponse? = null,
    val error: String = ""
)
