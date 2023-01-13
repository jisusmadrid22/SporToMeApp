package com.yzdev.sportome.presentation.screens.detail_match

import com.yzdev.sportome.domain.model.DetailMatchResponse

data class DetailMatchState(
    val error: String = "",
    val info: DetailMatchResponse? = null,
    val isLoading: Boolean = false
)
