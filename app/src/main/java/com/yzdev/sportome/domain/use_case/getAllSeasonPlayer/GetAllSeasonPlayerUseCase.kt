package com.yzdev.sportome.domain.use_case.getAllSeasonPlayer

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.common.dateProcess.getDifferenceAge
import com.yzdev.sportome.common.listProcess.getAllSeasonPlayerBySeasonMonth
import com.yzdev.sportome.domain.model.LocalSeasonPlayer
import com.yzdev.sportome.domain.model.LocalSeasons
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllSeasonPlayerUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(agePlayer: Int?): Flow<Resource<List<LocalSeasonPlayer>>> = flow {
        Log.e("seasonsPlayer", "into invoke")
        try {
            emit(Resource.Loading())
            val data = if (agePlayer != null) getAllSeasonPlayerBySeasonMonth(repo.getAllSeasonPlayer().takeLast(getDifferenceAge(ageTest = agePlayer))) else repo.getAllSeasonPlayer()
            emit(Resource.Success(data))

        } catch (e: HttpException){
            Log.e("seasonPlayer", "http exception -> ${e.message}")
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            Log.e("seasonPlayer", "io exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            Log.e("seasonPlayer", "generic exception -> ${e.message}")
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))
        }
    }
}