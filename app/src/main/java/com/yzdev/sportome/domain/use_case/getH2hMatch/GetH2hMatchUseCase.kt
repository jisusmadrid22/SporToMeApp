package com.yzdev.sportome.domain.use_case.getH2hMatch

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.DetailMatchResponse
import com.yzdev.sportome.domain.model.H2hResponse
import com.yzdev.sportome.domain.model.toDetailMatch
import com.yzdev.sportome.domain.model.toListH2hResponse
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetH2hMatchUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(ids: String) : Flow<Resource<List<H2hResponse>>> = flow {
        Log.e("match", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getH2hMatch(ids).toListH2hResponse()
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