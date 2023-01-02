package com.yzdev.sportome.domain.use_case.favoriteCompetition.getLocalFavoriteCompetitionById

import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.repository.AppRepository

class GetFavoriteCompetitionUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): LocalCompetition{
        return if (id != -1){
            repository.getLocalFavoriteCompetition(id = id)
        }else{
            throw InvalidException(AppResource.getString(R.string.erroGeneric))
        }
    }
}