package com.yzdev.sportome.presentation.screens.detail_match

import com.yzdev.sportome.domain.model.DetailMatchResponse
import com.yzdev.sportome.domain.model.H2hResponse

data class H2hMatchState(
    val error: String = "",
    val info: List<H2hResponse> = emptyList(),
    val isLoading: Boolean = false
)
