package com.yzdev.sportome.domain.use_case.getAllSeasonsYear

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalSeasons
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllSeasonsYearUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<LocalSeasons>>> = flow {
        Log.e("seasons", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getAllLocalSeasons()
            emit(Resource.Success(data))

        } catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            emit(Resource.Error(message =  e.localizedMessage ?: AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            emit(Resource.Error(message =  e.localizedMessage ?: AppResource.getString(R.string.erroGeneric)))

        }
    }
}