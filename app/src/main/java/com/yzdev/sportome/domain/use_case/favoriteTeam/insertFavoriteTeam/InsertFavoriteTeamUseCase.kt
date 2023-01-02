package com.yzdev.sportome.domain.use_case.favoriteTeam.insertFavoriteTeam

import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.domain.repository.AppRepository

class InsertFavoriteTeamUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(localTeam: LocalTeam){
        repository.insertFavoriteTeam(localTeam = localTeam)
    }
}