package com.yzdev.sportome.domain.use_case.favoriteTeam.getLocalFavoriteTeamById

import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.domain.repository.AppRepository

class GetFavoriteTeamUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: Int): LocalTeam {
        return if (id != -1){
            repository.getLocalFavoriteTeam(id = id)
        }else{
            throw InvalidException(AppResource.getString(R.string.erroGeneric))
        }
    }
}