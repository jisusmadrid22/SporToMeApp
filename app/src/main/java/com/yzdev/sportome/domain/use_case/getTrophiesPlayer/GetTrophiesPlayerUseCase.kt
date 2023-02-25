package com.yzdev.sportome.domain.use_case.getTrophiesPlayer

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.PlayerTrophiesResponse
import com.yzdev.sportome.domain.model.TransferPlayerResponse
import com.yzdev.sportome.domain.model.toPlayerTrophiesLocal
import com.yzdev.sportome.domain.model.toTransferPlayerLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTrophiesPlayerUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(playerId: Int): Flow<Resource<PlayerTrophiesResponse>> = flow {
        Log.e("trophiesPlayer", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getTrophiesPlayer(playerId)
            emit(Resource.Success(data.toPlayerTrophiesLocal()))

        } catch (e: HttpException){
            Log.e("trophiesPlayer", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("trophiesPlayer", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("trophiesPlayer", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}