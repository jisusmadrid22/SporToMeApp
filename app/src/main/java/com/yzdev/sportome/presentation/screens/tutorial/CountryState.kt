package com.yzdev.sportome.presentation.screens.tutorial

import com.yzdev.sportome.domain.model.LocalCountry

data class CountryState(
    val isLoading: Boolean = false,
    val info: List<LocalCountry>? = null,
    val error: String = ""
)
