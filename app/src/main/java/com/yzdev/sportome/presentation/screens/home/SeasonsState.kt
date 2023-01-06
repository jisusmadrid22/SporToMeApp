package com.yzdev.sportome.presentation.screens.home

import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalSeasons

data class SeasonsState(
    val isLoading: Boolean = false,
    val info: List<LocalSeasons>? = null,
    val error: String = ""
)
