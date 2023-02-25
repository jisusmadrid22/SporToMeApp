package com.yzdev.sportome.domain.use_case.favoriteTeam.deleteFavoriteTeam

import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.domain.repository.AppRepository

class DeleteFavoriteTeamUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(favoriteTeam: LocalTeam){
        repository.deleteFavoriteTeam(favoriteTeam)
    }
}