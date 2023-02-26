package com.yzdev.sportome.domain.use_case.getSeasonTeam

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.TeamSeasonLocalResponse
import com.yzdev.sportome.domain.model.toListLocalSeasons
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSeasonTeamUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(teamId: Int): Flow<Resource<TeamSeasonLocalResponse>> = flow {
        Log.e("seasonTeam", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getAllSeasonTeam(teamId)
            emit(Resource.Success(data.toListLocalSeasons()))

        } catch (e: HttpException){
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))

        }
    }
}