package com.yzdev.sportome.presentation.screens.player

import com.yzdev.sportome.domain.model.InfoPlayerResponse
import com.yzdev.sportome.domain.model.LocalSeasonPlayer

data class SeasonPlayerState(
    val isLoading: Boolean = false,
    val info: List<LocalSeasonPlayer>? = null,
    val error: String = ""
)
