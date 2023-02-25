package com.yzdev.sportome.domain.use_case.getRankedTeamForLeague

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.RankedCompetitionResponseLocal
import com.yzdev.sportome.domain.model.TransferPlayerResponse
import com.yzdev.sportome.domain.model.toRankedLocal
import com.yzdev.sportome.domain.model.toTransferPlayerLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRankedTeamForLeagueUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(leagueId: Int, season: Int): Flow<Resource<RankedCompetitionResponseLocal>> = flow {
        Log.e("ranked", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getRankedLeague(leagueId, season)
            emit(Resource.Success(data.toRankedLocal()))

        } catch (e: HttpException){
            Log.e("ranked", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("ranked", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("ranked", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}