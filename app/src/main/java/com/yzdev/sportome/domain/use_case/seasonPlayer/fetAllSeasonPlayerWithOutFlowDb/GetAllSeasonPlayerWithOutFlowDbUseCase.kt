package com.yzdev.sportome.domain.use_case.seasonPlayer.fetAllSeasonPlayerWithOutFlowDb

import com.yzdev.sportome.common.listProcess.getAllSeasonPlayerBySeasonMonth
import com.yzdev.sportome.domain.model.LocalSeasonPlayer
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllSeasonPlayerWithOutFlowDbUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<LocalSeasonPlayer> {
        val data = repository.getAllSeasonPlayerUnFormatWithOutFlow()

        return getAllSeasonPlayerBySeasonMonth(data)
    }
}