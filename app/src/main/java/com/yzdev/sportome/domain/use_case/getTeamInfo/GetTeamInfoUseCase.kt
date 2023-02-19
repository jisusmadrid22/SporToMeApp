package com.yzdev.sportome.domain.use_case.getTeamInfo

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.TeamInfoLocalResponse
import com.yzdev.sportome.domain.model.TransferPlayerResponse
import com.yzdev.sportome.domain.model.toTeamInfoLocal
import com.yzdev.sportome.domain.model.toTransferPlayerLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTeamInfoUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(idTeam: Int): Flow<Resource<TeamInfoLocalResponse>> = flow {
        Log.e("TeamInfo", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getTeamInfo(id = idTeam)
            emit(Resource.Success(data.toTeamInfoLocal()))

        } catch (e: HttpException){
            Log.e("TeamInfo", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("TeamInfo", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("TeamInfo", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}