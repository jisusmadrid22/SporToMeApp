package com.yzdev.sportome.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalSeasons
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

    /** get all favorite competition from db*/
    @Query("SELECT * FROM localcompetition")
    fun getAllLocalCompetition(): Flow<List<LocalCompetition>>

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
}