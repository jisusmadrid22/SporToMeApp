package com.yzdev.sportome.data.repository

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.*
import com.yzdev.sportome.data.data_source.AppDao
import com.yzdev.sportome.data.remote.ApiService
import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.RankedCompetitionDtoResponse
import com.yzdev.sportome.data.remote.dto.competition.TopScoresLeagueDtoResponse
import com.yzdev.sportome.data.remote.dto.match.DetailMatchDtoResponse
import com.yzdev.sportome.data.remote.dto.match.h2hResponseDto.H2hResponseDto
import com.yzdev.sportome.data.remote.dto.match.predictions.PredictionsResponseDto
import com.yzdev.sportome.data.remote.dto.player.InfoPlayerDto
import com.yzdev.sportome.data.remote.dto.player.PlayerTrophiesDto
import com.yzdev.sportome.data.remote.dto.player.TransferPlayerDto
import com.yzdev.sportome.data.remote.dto.team.TeamsDtoResponse
import com.yzdev.sportome.domain.model.*
import com.yzdev.sportome.domain.repository.AppRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepositoryImp @Inject constructor(
    private val dao: AppDao,
    private val api: ApiService
): AppRepository {

    //---------------------------------- API ----------------------------------------------

    /** get all leagues from api, query for current season and country
     * @param countryCode codeName of country
     * */
    override suspend fun getAllCompetitionRemoteQuery(countryCode: String): CompetitionDtoResponse {
        return api.getAllCurrentLeaguesByCountry(code = countryCode)
    }

    /** get all teams from api, query for current season and id league
     * @param leagueId id of league selected
     * @param yearSeason years season current of league selected
     * */
    override suspend fun getAllTeamsRemoteQuery(leagueId: Int, yearSeason: Int): TeamsDtoResponse {
        return api.getAllTeamByLeagueId(league = leagueId, season = yearSeason)
    }

    /** get all detail (stats, lineup, event, h2h) for match
     * @param id id of match
     * */
    override suspend fun getDetailMatch(id: Long): DetailMatchDtoResponse {
        return api.getMatchDetail(id)
    }

    /** get all h2h for match
     * @param h2h id string of id team home an id team away
     * */
    override suspend fun getH2hMatch(h2h: String): H2hResponseDto {
        return api.getH2hMatchDetail(h2h)
    }

    override suspend fun getPredictionMatch(idMatch: Int): PredictionsResponseDto {
        return api.getPredictionMatch(idMatch)
    }

    override suspend fun getPlayerInfo(playerId: Int, season: Int): InfoPlayerDto {
        return api.getPlayerInfoRemote(id = playerId, season = season)
    }

    override suspend fun getTransferPlayerRemote(playerId: Int): TransferPlayerDto {
        return api.getTransferPlayer(playerId)
    }

    override suspend fun getTrophiesPlayer(playerId: Int): PlayerTrophiesDto {
        return api.getTrophiesPlayer(playerId)
    }

    override suspend fun getRankedLeague(leagueId: Int, season: Int): RankedCompetitionDtoResponse {
        return api.getRankedLeague(league = leagueId, season = season)
    }

    override suspend fun getTopScoreForLeague(leagueId: Int, season: Int): TopScoresLeagueDtoResponse {
        return api.getTopScoresForLeague(league = leagueId, season = season)
    }

    //-------------------------------------------------------------------------------------

    //------------------------------------- DATA BASE --------------------------------------
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

    /** get all competition from db*/
    override suspend fun getAllLocalFavoriteCompetition(): Flow<List<LocalCompetition>> {
        return dao.getAllLocalCompetition().map { competition->
            competition.sortedBy { it.name.lowercase() }
        }
    }

    /** get query favorite competition from db
     * @param id id competition saved into db
     * */
    override suspend fun getLocalFavoriteCompetition(id: Int): LocalCompetition {
        return dao.getFavoriteLeagueById(id)
    }

    /** insert favorite competition into db
     * @param localCompetition competition will save into db
     * */
    override suspend fun insertFavoriteCompetition(localCompetition: LocalCompetition) {
        return dao.insertFavoriteCompetition(localCompetition)
    }

    /** delete favorite competition from db
     * @param favoriteCompetition competition saved into db
     * */
    override suspend fun deleteFavoriteCompetition(favoriteCompetition: LocalCompetition) {
        return dao.deleteFavoriteCompetition(favoriteCompetition)
    }

    /** get all seasons year from db or api*/
    override suspend fun getAllLocalSeasons(): List<LocalSeasons> {
        Log.e("seasons", "init fun seasons")
        var seasons = dao.getAllSeasonsYear()
        if(seasons.isEmpty()){
            //from api
            Log.e("countries", "from api countries")
            val apiSeasons = api.getAllSeasonYearRemote()

            dao.insertSeasons(apiSeasons.toListLocalSeasons())

            seasons = dao.getAllSeasonsYear()

        }else if(getHourDifference(timeToUnix() - seasons.first().timeRequest) >= 72){    //if hours is 72hr
            Log.e("seasons", "from api seasons update ${getHourDifference(timeToUnix() - seasons.first().timeRequest)} hr")
            val apiSeasons = api.getAllSeasonYearRemote()

            dao.insertSeasons(apiSeasons.toListLocalSeasons())

            seasons = dao.getAllSeasonsYear()
        }

        Log.e("seasons", "hours difference ${getHourDifference(timeToUnix() - seasons.first().timeRequest)} hr")
        return seasons
    }

    /** get all competition from db*/
    override suspend fun getAllLocalFavoriteTeam(): Flow<List<LocalTeam>> {
        return dao.getAllLocalTeam().map { team->
            team.sortedBy { it.name.lowercase() }
        }
    }

    /** get query favorite team from db
     * @param id id team saved into db
     * */
    override suspend fun getLocalFavoriteTeam(id: Int): LocalTeam {
        return dao.getFavoriteTeamById(id)
    }

    /** insert favorite team into db
     * @param localTeam team will save into db
     * */
    override suspend fun insertFavoriteTeam(localTeam: LocalTeam) {
        return dao.insertFavoriteTeam(localTeam)
    }

    /** delete favorite team from db
     * @param localTeam team saved into db
     * */
    override suspend fun deleteFavoriteTeam(localTeam: LocalTeam) {
        return dao.deleteFavoriteTeam(localTeam)
    }

    override suspend fun getAllSeasonPlayer(): List<LocalSeasonPlayer> {
        Log.e("seasonPlayer", "init fun")
        var seasons = dao.getAllSeasonPlayerWithoutFlow()
        if(seasons.isEmpty()){
            //from api
            Log.e("seasonPlayer", "from api")
            val apiSeasons = api.getAllSeasonsPlayer()

            dao.insertSeasonPlayer(apiSeasons.toLocalSeasonPlayer())

            seasons = dao.getAllSeasonPlayerWithoutFlow()

        }else if(getHourDifference(timeToUnix() - seasons.first().timeRequest) >= 72){    //if hours is 72hr
            Log.e("seasonPlayer", "from api seasons update ${getHourDifference(timeToUnix() - seasons.first().timeRequest)} hr")
            val apiSeasons = api.getAllSeasonsPlayer()

            dao.insertSeasonPlayer(apiSeasons.toLocalSeasonPlayer())

            seasons = dao.getAllSeasonPlayerWithoutFlow()
        }

        Log.e("seasonPlayer", "hours difference ${getHourDifference(timeToUnix() - seasons.first().timeRequest)} hr")
        return seasons
    }

    override suspend fun getWeekMatchesTeam(): List<LocalMatch> {
        val competitionFav = dao.getAllLocalCompetitionWithOutFlow()
        val teamsFav = dao.getAllLocalTeamWithoutFlow()
        var localMatch = dao.getAllLocalMatchWithoutFlow()
        val dateWeek = getAllDateOfWeek()

        val dateMatches = mutableListOf<LocalMatch>()

        if (localMatch.isEmpty()){

            //get calendar matches
            if (competitionFav.isNotEmpty()){
                if (teamsFav.isNotEmpty()){
                    competitionFav.forEach {
                        teamsFav.forEach {team->
                            val data = api.getAllMatchesTeamForThisWeekRemote(from = dateWeek.first(), to = dateWeek.last(), team = team.idApi, season = it.yearSeason).toListMatchesResponseLocal()

                            data.forEach {response->
                                dateMatches.add(LocalMatch(idMatch = response.fixture.id, idLeague = response.league.id, seasonYear = response.league.season, timestamp = timeToUnix(), matchDay = unixToDayWeek(response.fixture.timestamp).toInt()))
                            }
                            delay(1000)
                        }
                    }
                }else{
                    throw InvalidException(message = AppResource.getString(R.string.notTeamFavoriteForQueryWeek))
                }

            }else{
                throw InvalidException(message = AppResource.getString(R.string.notLeagueFavoriteForQueryWeek))
            }

            dao.insertListMatch(dateMatches)

            localMatch = dao.getAllLocalMatchWithoutFlow()

        }else if ((localMatch.first().timestamp > dateToUnix(dateWeek.first())) and (currentDayIsMonday())){
            //get calendar matches
            if (competitionFav.isNotEmpty()){
                if (teamsFav.isNotEmpty()){
                    competitionFav.forEach {
                        teamsFav.forEach {team->
                            val data = api.getAllMatchesTeamForThisWeekRemote(from = dateWeek.first(), to = dateWeek.last(), team = team.idApi, season = it.yearSeason).toListMatchesResponseLocal()

                            data.forEach {response->
                                dateMatches.add(LocalMatch(idMatch = response.fixture.id, idLeague = response.league.id, seasonYear = response.league.season, timestamp = timeToUnix(), matchDay = unixToDayWeek(response.fixture.timestamp).toInt()))
                            }
                            delay(1000)
                        }
                    }
                }else{
                    throw InvalidException(message = AppResource.getString(R.string.notTeamFavoriteForQueryWeek))
                }

            }else{
                throw InvalidException(message = AppResource.getString(R.string.notLeagueFavoriteForQueryWeek))
            }

            dao.insertListMatch(dateMatches)

            localMatch = dao.getAllLocalMatchWithoutFlow()

        }

        return localMatch
    }

    override suspend fun getAllMatchesTodayCompetition(): List<MatchLeagueResponse> {
        val competitionFav = dao.getAllLocalCompetitionWithOutFlow()
        val dateWeek = unixToDateTime(timeToUnix())

        val matchCompetition = mutableListOf<MatchLeagueResponse>()

        if (competitionFav.isNotEmpty()){
            competitionFav.forEach {
                val data = dateWeek?.let { it1 -> api.getAllMatchesCompetitionForThisWeekRemote(from = it1, to = it1, league = it.idApi, season = it.yearSeason).toListMatchesResponseLocal() }

                data?.let { response ->
                    matchCompetition.add(
                        MatchLeagueResponse(
                            nameLeague = it.name,
                            listMatch = response
                        )
                    )
                }
                delay(1000)
            }

        }else{
            throw InvalidException(message = AppResource.getString(R.string.notLeagueFavoriteForQueryWeek))
        }

        return matchCompetition
    }

    override suspend fun getAllMatchesTodayTeam(): List<MatchesResponseLocal> {
        val teamsFav = dao.getAllLocalTeamWithoutFlow()
        val listMatches = mutableListOf<MatchesResponseLocal>()
        val dateWeek = unixToDateTime(timeToUnix())

        teamsFav.forEach {
            dateWeek?.let { it1 ->
                val data = api.getAllMatchesTodayTeam(
                    team = it.idApi
                )

                listMatches.addAll(data.toListMatchesResponseLocal())
                delay(1000)
            }
        }

        return listMatches
    }

    //-------------------------------------------------------------------------------------
}