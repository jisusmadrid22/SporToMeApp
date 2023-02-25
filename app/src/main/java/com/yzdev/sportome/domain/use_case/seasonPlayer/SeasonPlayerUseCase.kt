package com.yzdev.sportome.domain.use_case.seasonPlayer

import com.yzdev.sportome.domain.use_case.seasonPlayer.fetAllSeasonPlayerWithOutFlowDb.GetAllSeasonPlayerWithOutFlowDbUseCase
import com.yzdev.sportome.domain.use_case.seasonPlayer.getAllSeasonPlayer.GetAllSeasonPlayerDbUseCase

data class SeasonPlayerUseCase(
    val getAllSeasonPlayerUseCase: GetAllSeasonPlayerDbUseCase,
    val getAllSeasonPlayerWithOutFlow: GetAllSeasonPlayerWithOutFlowDbUseCase
)
