package com.yzdev.sportome.presentation.screens.detail_match

import com.yzdev.sportome.domain.model.H2hResponse
import com.yzdev.sportome.domain.model.PredictionsResponse

data class PredictionMatchState(
    val error: String = "",
    val info: List<PredictionsResponse?> = emptyList(),
    val isLoading: Boolean = false
)
