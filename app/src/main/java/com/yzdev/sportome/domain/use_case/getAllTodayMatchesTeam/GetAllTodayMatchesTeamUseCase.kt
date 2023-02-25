package com.yzdev.sportome.domain.use_case.getAllTodayMatchesTeam

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.MatchesResponseLocal
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllTodayMatchesTeamUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(currentDay: String) : Flow<Resource<List<MatchesResponseLocal>>> = flow {
        Log.e("favorite match", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getAllMatchesTodayTeam()
            emit(Resource.Success(data))

        } catch (e: HttpException){
            Log.e("favorite match", "error http ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("favorite match", "error io ${e.message}")
            emit(Resource.Error(message =  e.message ?: AppResource.getString(R.string.apiServerError)))

        } catch (e: InvalidException){
            Log.e("favorite match", "invalid exception ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))

        } catch (e: Exception){
            Log.e("favorite match", "error generic ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))

        }
    }
}