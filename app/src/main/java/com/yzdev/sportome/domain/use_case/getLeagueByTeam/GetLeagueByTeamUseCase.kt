package com.yzdev.sportome.domain.use_case.getLeagueByTeam

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.TeamInfoLocalResponse
import com.yzdev.sportome.domain.model.toListLocalCompetition
import com.yzdev.sportome.domain.model.toTeamInfoLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLeagueByTeamUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(idTeam: Int, season: Int): Flow<Resource<List<LocalCompetition>>> = flow {
        Log.e("leagueTeam", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getLeagueByTeam(teamId = idTeam, season = season)
            emit(Resource.Success(data.toListLocalCompetition()))

        } catch (e: HttpException){
            Log.e("leagueTeam", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("TeamInfo", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("leagueTeam", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}