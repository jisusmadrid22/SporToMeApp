package com.yzdev.sportome.data.repository

import android.util.Log
import com.yzdev.sportome.R
import com.yzdev.sportome.common.*
import com.yzdev.sportome.data.data_source.AppDao
import com.yzdev.sportome.data.remote.ApiService
import com.yzdev.sportome.data.remote.dto.competition.CompetitionDtoResponse
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

    override suspend fun getWeekDataHome(): List<LocalMatch> {
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
                                dateMatches.add(LocalMatch(idMatch = response.fixture.id, idLeague = response.league.id, seasonYear = response.league.season, timestamp = timeToUnix()))
                            }
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
                                dateMatches.add(LocalMatch(idMatch = response.fixture.id, idLeague = response.league.id, seasonYear = response.league.season, timestamp = timeToUnix()))
                            }
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

    //-------------------------------------------------------------------------------------
}