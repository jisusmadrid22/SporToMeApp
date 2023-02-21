package com.yzdev.sportome.presentation.screens.player

import com.yzdev.sportome.domain.model.LocalSeasonPlayer
import com.yzdev.sportome.domain.model.PlayerTrophiesResponse

data class TrophiesPlayerState(
    val isLoading: Boolean = false,
    val info: PlayerTrophiesResponse? = null,
    val error: String = ""
)
