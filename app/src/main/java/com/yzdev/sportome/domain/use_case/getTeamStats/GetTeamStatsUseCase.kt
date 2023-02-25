package com.yzdev.sportome.domain.use_case.getTeamStats

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.TeamStatsLocalResponse
import com.yzdev.sportome.domain.model.TransferPlayerResponse
import com.yzdev.sportome.domain.model.toTeamStatsLocal
import com.yzdev.sportome.domain.model.toTransferPlayerLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTeamStatsUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(leagueId: Int, teamId: Int, season: Int): Flow<Resource<TeamStatsLocalResponse>> = flow {
        Log.e("teamStats", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getTeamStats(leagueId, teamId, season)
            emit(Resource.Success(data.toTeamStatsLocal()))

        } catch (e: HttpException){
            Log.e("teamStats", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("teamStats", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("teamStats", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}