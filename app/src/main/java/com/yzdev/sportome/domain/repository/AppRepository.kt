package com.yzdev.sportome.domain.repository

import com.yzdev.sportome.domain.model.LocalCountry
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    //---------------------------------- API ----------------------------------------------
    /** Get all countries from api
     * */
    //suspend fun getAllCountriesRemote():

    //-------------------------------------------------------------------------------------

    //------------------------------------- DATA BASE --------------------------------------

    /** get all countries from db*/
    suspend fun getAllLocalCountries(): List<LocalCountry>

    //-------------------------------------------------------------------------------------

}