package com.yzdev.sportome.presentation.screens.team

import com.yzdev.sportome.domain.model.TeamInfoLocalResponse
import com.yzdev.sportome.domain.model.TeamSeasonLocalResponse

data class TeamInfoState(
    val isLoading: Boolean = false,
    val info: TeamInfoLocalResponse? = null,
    val error: String = ""
)
