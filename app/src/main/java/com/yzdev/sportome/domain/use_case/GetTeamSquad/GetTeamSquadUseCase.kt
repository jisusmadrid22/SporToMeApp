package com.yzdev.sportome.domain.use_case.GetTeamSquad

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.TeamSquadLocalResponse
import com.yzdev.sportome.domain.model.TransferPlayerResponse
import com.yzdev.sportome.domain.model.toTeamSquadLocal
import com.yzdev.sportome.domain.model.toTransferPlayerLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTeamSquadUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(teamId: Int): Flow<Resource<TeamSquadLocalResponse>> = flow {
        Log.e("teamSquad", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getTeamSquad(teamId)
            emit(Resource.Success(data.toTeamSquadLocal()))

        } catch (e: HttpException){
            Log.e("teamSquad", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("teamSquad", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("teamSquad", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}