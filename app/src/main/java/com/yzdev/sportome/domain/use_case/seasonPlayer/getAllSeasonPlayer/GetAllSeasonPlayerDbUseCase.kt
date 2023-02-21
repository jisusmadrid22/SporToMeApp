package com.yzdev.sportome.domain.use_case.seasonPlayer.getAllSeasonPlayer

import com.yzdev.sportome.common.listProcess.getAllSeasonPlayerBySeasonMonth
import com.yzdev.sportome.domain.model.LocalSeasonPlayer
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetAllSeasonPlayerDbUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): Flow<List<LocalSeasonPlayer>>{
        return repository.getAllSeasonPlayerUnFormat().map {
            getAllSeasonPlayerBySeasonMonth(it)
        }
    }
}