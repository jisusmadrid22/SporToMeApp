package com.yzdev.sportome.data.repository

import android.util.Log
import com.yzdev.sportome.common.getHourDifference
import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.data.data_source.AppDao
import com.yzdev.sportome.data.remote.ApiService
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.toListLocalCountry
import com.yzdev.sportome.domain.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(
    private val dao: AppDao,
    private val api: ApiService
): AppRepository {

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
}