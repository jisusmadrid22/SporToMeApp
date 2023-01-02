package com.yzdev.sportome.domain.repository

import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalSeasons
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    //---------------------------------- API ----------------------------------------------
    /** get all competition from api by country code and current season*/
    suspend fun getAllCompetitionRemoteQuery(countryCode: String): CompetitionDtoResponse

    //-------------------------------------------------------------------------------------

    //------------------------------------- DATA BASE --------------------------------------

    /** COUNTRIES *********************************************************************/
    /** get all countries from db*/
    suspend fun getAllLocalCountries(): List<LocalCountry>

    /*** COMPETITIONS *******************************************************************/
    /** get all local favorite competition from db*/
    suspend fun getAllLocalFavoriteCompetition(): Flow<List<LocalCompetition>>

    /** get local favorite competition from db by id*/
    suspend fun getLocalFavoriteCompetition(id: Int): LocalCompetition

    /** insert favorite competition*/
    suspend fun insertFavoriteCompetition(localCompetition: LocalCompetition)

    /** delete favorite competition*/
    suspend fun deleteFavoriteCompetition(favoriteCompetition: LocalCompetition)

    /** SEASONS ***************************************************************************/
    /** get all seasons year from db*/
    suspend fun getAllLocalSeasons(): List<LocalSeasons>

    //-------------------------------------------------------------------------------------

}