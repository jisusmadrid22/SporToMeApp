package com.yzdev.sportome.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yzdev.sportome.domain.model.LocalCountry
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    /** COUNTRIES*/
    @Query("SELECT * FROM localcountry")
    suspend fun getAllCountry(): List<LocalCountry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountries(listCountries: List<LocalCountry>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: LocalCountry)

    @Delete
    suspend fun deleteCountry(country: LocalCountry)

    /**********************************************************************/
}