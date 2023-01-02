package com.yzdev.sportome.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
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

    /** insert country into db*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: LocalCountry)

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
}