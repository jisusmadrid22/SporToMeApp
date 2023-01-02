package com.yzdev.sportome.presentation.screens.tutorial

import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalTeam

data class TeamState(
    val isLoading: Boolean = false,
    val info: List<LocalTeam>? = null,
    val error: String = ""
)
