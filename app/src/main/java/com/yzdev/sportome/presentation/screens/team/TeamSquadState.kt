package com.yzdev.sportome.presentation.screens.team

import com.yzdev.sportome.domain.model.TeamSquadLocalResponse

data class TeamSquadState(
    val isLoading: Boolean = false,
    val info: TeamSquadLocalResponse? = null,
    val error: String = ""
)
