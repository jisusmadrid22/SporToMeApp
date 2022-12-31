package com.yzdev.sportome.data.repository

import android.util.Log
import com.yzdev.sportome.common.getCurrentDay
import com.yzdev.sportome.common.getHourDifference
import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.data.data_source.AppDao
import com.yzdev.sportome.data.remote.ApiService
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.flow.*
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
            val apiCountries = getCountriesLocal()

            dao.insertCountries(apiCountries)

            countries = dao.getAllCountry()

        }else if(getHourDifference(timeToUnix() - countries.first().timeRequest) >= 72){    //if hours is 72hr
            Log.e("countries", "from api countries update ${getHourDifference(timeToUnix() - countries.first().timeRequest)} hr")
            val apiCountries = getCountriesLocal()

            dao.insertCountries(apiCountries)

            countries = dao.getAllCountry()
        }

        Log.e("countries", "hours difference ${getHourDifference(timeToUnix() - countries.first().timeRequest)} hr")
        return countries
    }
}

fun getCountriesLocal(): List<LocalCountry>{
    val timestampCurrent = timeToUnix()
    return listOf(
        LocalCountry(
            idApi = 1,
            name = "Venezuela",
            code = "VE",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 2,
            name = "Spain",
            code = "ES",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 3,
            name = "United State",
            code = "US",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 4,
            name = "Germany",
            code = "GE",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 5,
            name = "Argentina",
            code = "AR",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 6,
            name = "Peru",
            code = "PE",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 7,
            name = "Colombia",
            code = "CO",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 8,
            name = "Chile",
            code = "CH",
            timeRequest = timestampCurrent
        ),
        LocalCountry(
            idApi = 9,
            name = "Mexico",
            code = "ME",
            timeRequest = timestampCurrent
        )
    )
}