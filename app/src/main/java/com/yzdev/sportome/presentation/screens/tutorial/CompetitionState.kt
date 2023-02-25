package com.yzdev.sportome.presentation.screens.tutorial

import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry

data class CompetitionState(
    val isLoading: Boolean = false,
    val info: List<LocalCompetition>? = null,
    val error: String = ""
)
