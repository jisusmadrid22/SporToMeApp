package com.yzdev.sportome.data.remote

import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.SeasonsDtoResponse
import com.yzdev.sportome.data.remote.dto.countries.CountriesDtoResponse
import com.yzdev.sportome.data.remote.dto.match.MatchesForThisWeekDtoResponse
import com.yzdev.sportome.data.remote.dto.team.TeamsDtoResponse
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

    /** get all teams of the league from api
     * @param league id api of league
     * */
    @GET("teams")
    suspend fun getAllTeamByLeagueId(@Query("league") league: Int, @Query("season") season: Int): TeamsDtoResponse

    /** get all matches for this week
     * @param from date init week
     * @param to date finish week
     * @param league id api of league
     * */
    @GET("fixtures")
    suspend fun getAllMatchesForThisWeekRemote(@Query("from") from: String, @Query("to") to: String, @Query("league") league: Int, @Query("season") season: Int): MatchesForThisWeekDtoResponse

    @GET("fixtures")
    suspend fun getAllMatchesTeamForThisWeekRemote(@Query("from") from: String, @Query("to") to: String, @Query("team") team: Int, @Query("season") season: Int): MatchesForThisWeekDtoResponse




}