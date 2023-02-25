package com.yzdev.sportome.presentation.screens.player

import com.yzdev.sportome.domain.model.InfoPlayerResponse
import com.yzdev.sportome.domain.model.LocalSeasons

data class PlayerResumeState(
    val isLoading: Boolean = false,
    val info: InfoPlayerResponse? = null,
    val error: String = ""
)
