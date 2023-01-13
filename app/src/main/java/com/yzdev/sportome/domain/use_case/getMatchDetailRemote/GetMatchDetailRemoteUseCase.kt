package com.yzdev.sportome.domain.use_case.getMatchDetailRemote

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.InvalidException
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.DetailMatchResponse
import com.yzdev.sportome.domain.model.MatchesResponseLocal
import com.yzdev.sportome.domain.model.toDetailMatch
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMatchDetailRemoteUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(id: Long) : Flow<Resource<DetailMatchResponse>> = flow {
        Log.e("match", "into invoke")
        try {
            emit(Resource.Loading())
            val data = repo.getDetailMatch(id).toDetailMatch()
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