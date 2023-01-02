package com.yzdev.sportome.domain.repository

import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.team.TeamsDtoResponse
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalSeasons
import com.yzdev.sportome.domain.model.LocalTeam
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    //---------------------------------- API ----------------------------------------------
    /** get all competition from api by country code and current season*/
    suspend fun getAllCompetitionRemoteQuery(countryCode: String): CompetitionDtoResponse

    suspend fun getAllTeamsRemoteQuery(leagueId: Int, yearSeason: Int): TeamsDtoResponse

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

    /*** TEAMS *******************************************************************/
    /** get all local favorite team from db*/
    suspend fun getAllLocalFavoriteTeam(): Flow<List<LocalTeam>>

    /** get local favorite team from db by id*/
    suspend fun getLocalFavoriteTeam(id: Int): LocalTeam

    /** insert favorite team*/
    suspend fun insertFavoriteTeam(localTeam: LocalTeam)

    /** delete favorite team*/
    suspend fun deleteFavoriteTeam(localTeam: LocalTeam)

    //-------------------------------------------------------------------------------------

}