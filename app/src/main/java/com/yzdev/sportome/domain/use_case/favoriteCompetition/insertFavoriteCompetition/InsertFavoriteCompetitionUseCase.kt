package com.yzdev.sportome.domain.use_case.favoriteCompetition.insertFavoriteCompetition

import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.repository.AppRepository

/** insert favorite competition into db*/
class InsertFavoriteCompetitionUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(localCompetition: LocalCompetition){
        repository.insertFavoriteCompetition(localCompetition = localCompetition)
    }
}