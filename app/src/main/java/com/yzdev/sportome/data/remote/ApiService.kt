package com.yzdev.sportome.data.remote

import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.SeasonsDtoResponse
import com.yzdev.sportome.data.remote.dto.countries.CountriesDtoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    /** get all countries from api*/
    @GET("countries")
    suspend fun getAllCountriesRemote(): CountriesDtoResponse

    /** get all leagues from api, query for current season and country
     * @param code codeName of country
     * */
    @GET("leagues")
    suspend fun getAllCurrentLeaguesByCountry(@Query("code") code: String, @Query("current") current: Boolean = true): CompetitionDtoResponse

    /** get all seasons year from api*/
    @GET("leagues/seasons")
    suspend fun getAllSeasonYearRemote(): SeasonsDtoResponse
}