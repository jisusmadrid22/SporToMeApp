package com.yzdev.sportome.data.remote

import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.RankedCompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.SeasonsDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.TopScoresLeagueDtoResponse
import com.yzdev.sportome.data.remote.dto.countries.CountriesDtoResponse
import com.yzdev.sportome.data.remote.dto.match.DetailMatchDtoResponse
import com.yzdev.sportome.data.remote.dto.match.MatchesForThisWeekDtoResponse
import com.yzdev.sportome.data.remote.dto.match.h2hResponseDto.H2hResponseDto
import com.yzdev.sportome.data.remote.dto.match.predictions.PredictionsResponseDto
import com.yzdev.sportome.data.remote.dto.player.AllSeasonPlayerDto
import com.yzdev.sportome.data.remote.dto.player.InfoPlayerDto
import com.yzdev.sportome.data.remote.dto.player.PlayerTrophiesDto
import com.yzdev.sportome.data.remote.dto.player.TransferPlayerDto
import com.yzdev.sportome.data.remote.dto.team.*
import retrofit2.http.GET
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

    @GET("leagues")
    suspend fun getAllLeagueByTeam(@Query("team") team: Int, @Query("season") season: Int): CompetitionDtoResponse

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
    suspend fun getAllMatchesCompetitionForThisWeekRemote(@Query("from") from: String, @Query("to") to: String, @Query("league") league: Int, @Query("season") season: Int): MatchesForThisWeekDtoResponse

    @GET("fixtures")
    suspend fun getAllMatchesTeamForThisWeekRemote(@Query("from") from: String, @Query("to") to: String, @Query("team") team: Int, @Query("season") season: Int): MatchesForThisWeekDtoResponse

    @GET("fixtures")
    suspend fun getAllMatchesTodayTeam(@Query("team") team: Int, @Query("live") live: String = "all"): MatchesForThisWeekDtoResponse

    @GET("fixtures")
    suspend fun getMatchDetail(@Query("id") id: Long): DetailMatchDtoResponse

    @GET("fixtures/headtohead")
    suspend fun getH2hMatchDetail(@Query("h2h") h2h: String): H2hResponseDto

    @GET("predictions")
    suspend fun getPredictionMatch(@Query("fixture") fixture: Int): PredictionsResponseDto

    @GET("players/seasons")
    suspend fun getAllSeasonsPlayer(): AllSeasonPlayerDto

    @GET("players")
    suspend fun getPlayerInfoRemote(@Query("id") id: Int, @Query("season") season: Int): InfoPlayerDto

    @GET("transfers")
    suspend fun getTransferPlayer(@Query("player") player: Int): TransferPlayerDto

    @GET("trophies")
    suspend fun getTrophiesPlayer(@Query("player") player: Int): PlayerTrophiesDto

    @GET("standings")
    suspend fun getRankedLeague(@Query("league") league: Int, @Query("season") season: Int): RankedCompetitionDtoResponse

    @GET("standings")
    suspend fun getTopScoresForLeague(@Query("league") league: Int, @Query("season") season: Int): TopScoresLeagueDtoResponse

    @GET("teams")
    suspend fun getTeamInfo(@Query("id") id: Int): TeamInfoDtoResponse

    @GET("teams/statistics")
    suspend fun getTeamStats(@Query("league") league: Int, @Query("season") season: Int, @Query("team") team: Int): TeamStatsDtoResponse

    @GET("players/squads")
    suspend fun getTeamSquad(@Query("team") team: Int): TeamSquadDtoResponse

    /** get all seasons year from api*/
    @GET("teams/seasons")
    suspend fun getAllSeasonYearTeam(@Query("team") team: Int): TeamSeasonDtoResponse
}