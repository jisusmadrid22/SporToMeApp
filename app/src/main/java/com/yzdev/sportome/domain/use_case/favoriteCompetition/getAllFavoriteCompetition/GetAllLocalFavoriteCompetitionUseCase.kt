package com.yzdev.sportome.domain.use_case.favoriteCompetition.getAllFavoriteCompetition

import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllLocalFavoriteCompetitionUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): Flow<List<LocalCompetition>>{
        return repository.getAllLocalFavoriteCompetition()
    }
}