package com.yzdev.sportome.domain.use_case.favoriteTeam

import com.yzdev.sportome.domain.use_case.favoriteTeam.deleteFavoriteTeam.DeleteFavoriteTeamUseCase
import com.yzdev.sportome.domain.use_case.favoriteTeam.getAllFavoriteTeam.GetAllLocalFavoriteTeamUseCase
import com.yzdev.sportome.domain.use_case.favoriteTeam.getLocalFavoriteTeamById.GetFavoriteTeamUseCase
import com.yzdev.sportome.domain.use_case.favoriteTeam.insertFavoriteTeam.InsertFavoriteTeamUseCase

data class TeamUseCaseFormat(
    val getAllLocalFavoriteTeamUseCase: GetAllLocalFavoriteTeamUseCase,
    val getFavoriteTeamUseCase: GetFavoriteTeamUseCase,
    val insertFavoriteTeamUseCase: InsertFavoriteTeamUseCase,
    val deleteFavoriteTeamUseCase: DeleteFavoriteTeamUseCase
)
