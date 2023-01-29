package com.yzdev.sportome.domain.use_case.getPredictionMatch

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.DetailMatchResponse
import com.yzdev.sportome.domain.model.PredictionsResponse
import com.yzdev.sportome.domain.model.toDetailMatch
import com.yzdev.sportome.domain.model.toListPredictionResponse
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPredictionMatchUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(id: Int) : Flow<Resource<List<PredictionsResponse?>>> = flow {
        Log.e("prediction", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getPredictionMatch(id).toListPredictionResponse()
            emit(Resource.Success(data))
        } catch (e: HttpException){
            emit(Resource.Error(message = e.message ?: AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            emit(Resource.Error(message =  e.message ?: AppResource.getString(R.string.apiServerError)))

        } catch (e: InvalidException){
            emit(Resource.Error(message =  e.message ?: AppResource.getString(R.string.erroGeneric)))

        } catch (e: Exception){
            emit(Resource.Error(message =  e.message ?: AppResource.getString(R.string.erroGeneric)))

        }
    }
}