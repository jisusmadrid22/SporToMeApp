package com.yzdev.sportome.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yzdev.sportome.domain.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    /** COUNTRIES*/

    /** get all countries from db*/
    @Query("SELECT * FROM localcountry")
    suspend fun getAllCountry(): List<LocalCountry>

    /** insert list countries into db*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(listCountries: List<LocalCountry>)

    /** delete country from db*/
    @Delete
    suspend fun deleteCountry(country: LocalCountry)

    /**********************************************************************/

    /** LEAGUES*/

    /** get all favorite competition from db flow*/
    @Query("SELECT * FROM localcompetition")
    fun getAllLocalCompetition(): Flow<List<LocalCompetition>>

    /** get all favorite competition from db*/
    @Query("SELECT * FROM localcompetition")
    suspend fun getAllLocalCompetitionWithOutFlow(): List<LocalCompetition>

    /** get favorite competition from db*/
    @Query("SELECT * FROM localcompetition WHERE id = :id")
    suspend fun getFavoriteLeagueById(id: Int): LocalCompetition

    /** insert favorite competition from db*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteCompetition(favoriteCompetition: LocalCompetition)

    /** delete favorite competition from db*/
    @Delete
    suspend fun deleteFavoriteCompetition(favoriteCompetition: LocalCompetition)

    /************************************************************************/

    /** SEASONS YEAR*/

    /** get all seasons year from db*/
    @Query("SELECT * FROM localseasons")
    suspend fun getAllSeasonsYear(): List<LocalSeasons>

    /** insert list countries into db*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeasons(listSeasons: List<LocalSeasons>)

    /** delete season from db*/
    @Delete
    suspend fun deleteSeason(season: LocalSeasons)

    /************************************************************************/

    /** TEAMS*/
    /** get all favorite team from db*/
    @Query("SELECT * FROM localteam")
    fun getAllLocalTeam(): Flow<List<LocalTeam>>

    /** get all favorite team from db*/
    @Query("SELECT * FROM localteam")
    suspend fun getAllLocalTeamWithoutFlow(): List<LocalTeam>

    /** get favorite team from db*/
    @Query("SELECT * FROM localteam WHERE id = :id")
    suspend fun getFavoriteTeamById(id: Int): LocalTeam

    /** insert favorite team from db*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteTeam(favoriteTeam: LocalTeam)

    /** delete favorite team from db*/
    @Delete
    suspend fun deleteFavoriteTeam(favoriteTeam: LocalTeam)

    /** LOCAL MATCH DATE*/
    /** get all local match from db*/
    @Query("SELECT * FROM localmatch")
    fun getAllLocalMatch(): Flow<List<LocalMatch>>

    /** get all favorite match from db*/
    @Query("SELECT * FROM localmatch")
    suspend fun getAllLocalMatchWithoutFlow(): List<LocalMatch>

    /** get favorite match from db*/
    @Query("SELECT * FROM localmatch WHERE id = :id")
    suspend fun getMatchById(id: Int): LocalMatch

    /** insert favorite match from db*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatch(match: LocalMatch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMatch(listMatch: List<LocalMatch>)

    /** delete favorite match from db*/
    @Delete
    suspend fun deleteMatch(match: LocalMatch)

    /************************************************************************/
}