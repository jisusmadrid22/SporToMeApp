package com.yzdev.sportome.presentation.screens.today_match

import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalMatch
import com.yzdev.sportome.domain.model.MatchesResponseLocal

data class MatchesWeekState(
    val isLoading: Boolean = false,
    val info: List<LocalMatch>? = null,
    val error: String = ""
)
