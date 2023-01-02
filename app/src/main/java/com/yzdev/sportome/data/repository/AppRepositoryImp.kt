package com.yzdev.sportome.data.repository

import android.util.Log
import com.yzdev.sportome.common.getHourDifference
import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.data.data_source.AppDao
import com.yzdev.sportome.data.remote.ApiService
import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.toListLocalCountry
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(
    private val dao: AppDao,
    private val api: ApiService
): AppRepository {

    //---------------------------------- API ----------------------------------------------

    /** get all leagues from api, query for current season and country
     * @param countryCode codeName of country
     * */
    override suspend fun getAllCompetitionRemoteQuery(countryCode: String): CompetitionDtoResponse {
        return api.getAllCurrentLeaguesByCountry(code = countryCode)
    }

    //-------------------------------------------------------------------------------------

    //------------------------------------- DATA BASE --------------------------------------
    /** get all country from api or db*/
    override suspend fun getAllLocalCountries(): List<LocalCountry> {
        Log.e("countries", "init fun")
        var countries = dao.getAllCountry()
        if(countries.isEmpty()){
            //from api
            Log.e("countries", "from api countries")
            val apiCountries = api.getAllCountriesRemote()

            dao.insertCountries(apiCountries.toListLocalCountry())

            countries = dao.getAllCountry()

        }else if(getHourDifference(timeToUnix() - countries.first().timeRequest) >= 72){    //if hours is 72hr
            Log.e("countries", "from api countries update ${getHourDifference(timeToUnix() - countries.first().timeRequest)} hr")
            val apiCountries = api.getAllCountriesRemote()

            dao.insertCountries(apiCountries.toListLocalCountry())

            countries = dao.getAllCountry()
        }

        Log.e("countries", "hours difference ${getHourDifference(timeToUnix() - countries.first().timeRequest)} hr")
        return countries
    }

    /** get all competition from db*/
    override suspend fun getAllLocalFavoriteCompetition(): Flow<List<LocalCompetition>> {
        return dao.getAllLocalCompetition().map { competition->
            competition.sortedBy { it.name.lowercase() }
        }
    }

    /** get query favorite competition from db
     * @param id id competition saved into db
     * */
    override suspend fun getLocalFavoriteCompetition(id: Int): LocalCompetition {
        return dao.getFavoriteLeagueById(id)
    }

    /** insert favorite competition into db
     * @param localCompetition competition will save into db
     * */
    override suspend fun insertFavoriteCompetition(localCompetition: LocalCompetition) {
        return dao.insertFavoriteCompetition(localCompetition)
    }

    /** delete favorite competition from db
     * @param id id competition saved into db
     * */
    override suspend fun deleteFavoriteCompetition(favoriteCompetition: LocalCompetition) {
        return dao.deleteFavoriteCompetition(favoriteCompetition)
    }

    //-------------------------------------------------------------------------------------
}