package com.yzdev.sportome.domain.use_case.getTransferPlayer

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.InfoPlayerResponse
import com.yzdev.sportome.domain.model.TransferPlayerResponse
import com.yzdev.sportome.domain.model.toInfoPlayerLocal
import com.yzdev.sportome.domain.model.toTransferPlayerLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTransferPlayerUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(playerId: Int): Flow<Resource<TransferPlayerResponse>> = flow {
        Log.e("infoPlayer", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getTransferPlayerRemote(playerId)
            emit(Resource.Success(data.toTransferPlayerLocal()))

        } catch (e: HttpException){
            Log.e("infoPlayer", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("infoPlayer", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("infoPlayer", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}