package com.yzdev.sportome.domain.use_case.getTopScoreLeague

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.TopScoresLeagueLocalResponse
import com.yzdev.sportome.domain.model.TransferPlayerResponse
import com.yzdev.sportome.domain.model.toTopScoresLeagueLocal
import com.yzdev.sportome.domain.model.toTransferPlayerLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTopScoresLeagueUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(leagueId: Int, season: Int): Flow<Resource<TopScoresLeagueLocalResponse>> = flow {
        Log.e("topScore", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getTopScoreForLeague(leagueId, season)
            emit(Resource.Success(data.toTopScoresLeagueLocal()))

        } catch (e: HttpException){
            Log.e("topScore", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("topScore", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("topScore", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}