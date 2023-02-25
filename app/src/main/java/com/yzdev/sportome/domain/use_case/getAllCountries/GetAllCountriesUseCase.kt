package com.yzdev.sportome.domain.use_case.getAllCountries

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/** get all countries use case*/
class GetAllCountriesUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<LocalCountry>>> = flow {
        Log.e("countries", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getAllLocalCountries()
            emit(Resource.Success(data))

        } catch (e: HttpException){
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))

        }
    }
}