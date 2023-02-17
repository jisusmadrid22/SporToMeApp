package com.yzdev.sportome.domain.repository

import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.RankedCompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.match.DetailMatchDtoResponse
import com.yzdev.sportome.data.remote.dto.match.h2hResponseDto.H2hResponseDto
import com.yzdev.sportome.data.remote.dto.match.predictions.PredictionsResponseDto
import com.yzdev.sportome.data.remote.dto.player.InfoPlayerDto
import com.yzdev.sportome.data.remote.dto.player.PlayerTrophiesDto
import com.yzdev.sportome.data.remote.dto.player.TransferPlayerDto
import com.yzdev.sportome.data.remote.dto.team.TeamsDtoResponse
import com.yzdev.sportome.domain.model.*
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    //---------------------------------- API ----------------------------------------------
    /** get all competition from api by country code and current season*/
    suspend fun getAllCompetitionRemoteQuery(countryCode: String): CompetitionDtoResponse

    suspend fun getAllTeamsRemoteQuery(leagueId: Int, yearSeason: Int): TeamsDtoResponse

    suspend fun getDetailMatch(id: Long): DetailMatchDtoResponse

    suspend fun getH2hMatch(h2h: String): H2hResponseDto

    suspend fun getPredictionMatch(idMatch: Int): PredictionsResponseDto

    suspend fun getPlayerInfo(playerId: Int, season: Int): InfoPlayerDto

    suspend fun getTransferPlayerRemote(playerId: Int): TransferPlayerDto

    suspend fun getTrophiesPlayer(playerId: Int): PlayerTrophiesDto

    suspend fun getRankedLeague(leagueId: Int, season: Int): RankedCompetitionDtoResponse

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

    /*************** PLAYER ****************************************************/
    /** get all seasons year from db*/
    suspend fun getAllSeasonPlayer(): List<LocalSeasonPlayer>

    /***************************************************************************/

    //-------------------------------------------------------------------------------------

    //HYBRID

    /** get week matches team*/
    suspend fun getWeekMatchesTeam(): List<LocalMatch>

    /** get today matches competition*/
    suspend fun getAllMatchesTodayCompetition(): List<MatchLeagueResponse>

    /** get today matches team*/
    suspend fun getAllMatchesTodayTeam(): List<MatchesResponseLocal>
    // -------------------------------------------------------------------------------------
}