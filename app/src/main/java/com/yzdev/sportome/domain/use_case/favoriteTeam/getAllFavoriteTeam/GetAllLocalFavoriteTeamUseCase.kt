package com.yzdev.sportome.domain.use_case.favoriteTeam.getAllFavoriteTeam

import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow

class GetAllLocalFavoriteTeamUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): Flow<List<LocalTeam>> {
        return repository.getAllLocalFavoriteTeam()
    }
}