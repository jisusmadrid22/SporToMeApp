package com.yzdev.sportome.domain.use_case.getAllMatchesWeek

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalMatch
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.delay
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
            val data = repo.getWeekMatchesTeam()
            emit(Resource.Success(data))

        } catch (e: HttpException){
            Log.e("week", "error http ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("week", "error io ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: InvalidException){
            Log.e("week", "invalid exception ${e.message}")
            emit(Resource.Error(message =  e.message ?: AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("week", "error generic ${e.message}")
            emit(Resource.Error(message =  e.message ?: AppResource.getString(R.string.erroGeneric)))

        }
    }
}