package com.yzdev.sportome.data.remote

import com.yzdev.sportome.data.remote.dto.countries.CountriesDtoResponse
import retrofit2.http.GET

interface ApiService {

    /** get all countries from api*/
    @GET("countries")
    suspend fun getAllCountriesRemote(): CountriesDtoResponse
}