package com.yzdev.sportome.domain.use_case.getAllCompetitionQueryRemote

import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.toListLocalCompetition
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCompetitionQueryRemoteUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(countryCode: String): Flow<Resource<List<LocalCompetition>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repo.getAllCompetitionRemoteQuery(countryCode)
            emit(Resource.Success(data.toListLocalCompetition().sortedBy { it.name.lowercase() }))

        } catch (e: HttpException){
            emit(Resource.Error(message = AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            emit(Resource.Error(message =  AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            emit(Resource.Error(message =  AppResource.getString(R.string.erroGeneric)))

        }
    }
}