package com.yzdev.sportome.presentation.screens.player

import com.yzdev.sportome.domain.model.LocalSeasonPlayer
import com.yzdev.sportome.domain.model.TransferPlayerResponse

data class CareerPlayerState(
    val isLoading: Boolean = false,
    val info: TransferPlayerResponse? = null,
    val error: String = ""
)
