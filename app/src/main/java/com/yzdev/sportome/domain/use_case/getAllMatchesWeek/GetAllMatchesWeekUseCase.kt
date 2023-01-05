package com.yzdev.sportome.domain.use_case.getAllMatchesWeek

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalMatch
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMatchesWeekUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<LocalMatch>>> = flow {
        Log.e("week", "into invoke week")
        try {
            emit(Resource.Loading())
            val data = repo.getWeekDataHome()
            emit(Resource.Success(data))

        } catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            emit(Resource.Error(message =  e.localizedMessage ?: AppResource.getString(R.string.apiServerError)))

        } catch (e: InvalidException){
            emit(Resource.Error(message =  e.message ?: AppResource.getString(R.string.erroGeneric)))

        } catch (e: Exception){
            emit(Resource.Error(message =  e.localizedMessage ?: AppResource.getString(R.string.erroGeneric)))

        }
    }
}