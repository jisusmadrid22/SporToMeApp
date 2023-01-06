package com.yzdev.sportome.domain.use_case.favoriteCompetition.deleteFavoriteCompetition

import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.repository.AppRepository

/** delete favorite competition use case*/
class DeleteFavoriteCompetitionUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(favoriteCompetition: LocalCompetition){
        repository.deleteFavoriteCompetition(favoriteCompetition)
    }
}