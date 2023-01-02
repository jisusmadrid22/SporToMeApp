package com.yzdev.sportome.domain.use_case.getAllTeamsQueryRemote

import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Resource
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalTeam
import com.yzdev.sportome.domain.model.toListLocalCompetition
import com.yzdev.sportome.domain.model.toListLocalTeam
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllTeamsQueryRemoteUseCase @Inject constructor(
    private val repo: AppRepository
) {
    suspend operator fun invoke(leagueId: Int, yearSeason: Int): Flow<Resource<List<LocalTeam>>> = flow {
        try {
            emit(Resource.Loading())
            val data = repo.getAllTeamsRemoteQuery(leagueId = leagueId, yearSeason = yearSeason)
            emit(Resource.Success(data.toListLocalTeam().sortedBy { it.name.lowercase() }))

        } catch (e: HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: AppResource.getString(R.string.erroGeneric)))

        } catch (e: IOException){
            emit(Resource.Error(message =  e.localizedMessage ?: AppResource.getString(R.string.apiServerError)))

        } catch (e: Exception){
            emit(Resource.Error(message =  e.localizedMessage ?: AppResource.getString(R.string.erroGeneric)))

        }
    }
}