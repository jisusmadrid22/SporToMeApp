package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.competition.RankedCompetitionDtoResponse

data class RankedCompetitionResponseLocal(
    val response: List<Response>,
) {
    data class Response(
        val league: League
    ) {
        data class League(
            val country: String,
            val flag: String?,
            val id: Int,
            val logo: String?,
            val name: String,
            val season: Int,
            val standings: List<List<Standing>>
        ) {
            data class Standing(
                val all: All,
                val away: Away,
                val description: String?,
                val form: String?,
                val goalsDiff: Int?,
                val group: String?,
                val home: Home,
                val points: Int?,
                val rank: Int?,
                val status: String?,
                val team: Team,
                val update: String?
            ) {
                data class All(
                    val draw: Int?,
                    val goals: Goals,
                    val lose: Int?,
                    val played: Int?,
                    val win: Int?
                ) {
                    data class Goals(
                        val against: Int?,
                        val forTeam: Int?
                    )
                }

                data class Away(
                    val draw: Int?,
                    val goals: Goals,
                    val lose: Int?,
                    val played: Int?,
                    val win: Int?
                ) {
                    data class Goals(
                        val against: Int?,
                        val forTeam: Int?
                    )
                }

                data class Home(
                    val draw: Int?,
                    val goals: Goals,
                    val lose: Int?,
                    val played: Int?,
                    val win: Int?
                ) {
                    data class Goals(
                        val against: Int?,
                        val forTeam: Int?
                    )
                }

                data class Team(
                    val id: Int?,
                    val logo: String?,
                    val name: String?
                )
            }
        }
    }
}

/** mapper*/

fun RankedCompetitionDtoResponse.toRankedLocal(): RankedCompetitionResponseLocal{
    return RankedCompetitionResponseLocal(
        response = response.toRankedLocalResponse()
    )
}

private fun List<RankedCompetitionDtoResponse.Response>.toRankedLocalResponse(): List<RankedCompetitionResponseLocal.Response>{
    return this.map {
        RankedCompetitionResponseLocal.Response(
            league = it.league.toRankedLocalResponseLeague()
        )
    }
}

private fun RankedCompetitionDtoResponse.Response.League.toRankedLocalResponseLeague(): RankedCompetitionResponseLocal.Response.League{
    return RankedCompetitionResponseLocal.Response.League(
        country = country,
        flag = flag,
        id = id,
        logo = logo,
        name = name,
        season = season,
        standings = standings.toRankedLocalResponseLeagueStanding()
    )
}

private fun List<List<RankedCompetitionDtoResponse.Response.League.Standing>>.toRankedLocalResponseLeagueStanding(): List<List<RankedCompetitionResponseLocal.Response.League.Standing>>{
    return this.map {parent->
        parent.map {
            RankedCompetitionResponseLocal.Response.League.Standing(
                all = it.all.toRankedLocalResponseLeagueStandingAll(),
                away = it.away.toRankedLocalResponseLeagueStandingAway(),
                description = it.description,
                form = it.form,
                goalsDiff = it.goalsDiff,
                group = it.group,
                home = it.home.toRankedLocalResponseLeagueStandingAll(),
                points = it.points,
                rank = it.rank,
                status = it.status,
                team = it.team.toRankedLocalResponseLeagueStandingTeam(),
                update = it.update
            )
        }
    }
}

private fun RankedCompetitionDtoResponse.Response.League.Standing.All.toRankedLocalResponseLeagueStandingAll(): RankedCompetitionResponseLocal.Response.League.Standing.All{
    return RankedCompetitionResponseLocal.Response.League.Standing.All(
        draw = draw,
        goals = goals.toRankedLocalResponseLeagueStandingAllGoals(),
        lose = lose,
        played = played,
        win = win
    )
}

private fun RankedCompetitionDtoResponse.Response.League.Standing.All.Goals.toRankedLocalResponseLeagueStandingAllGoals(): RankedCompetitionResponseLocal.Response.League.Standing.All.Goals{
    return RankedCompetitionResponseLocal.Response.League.Standing.All.Goals(
        against, forTeam
    )
}

private fun RankedCompetitionDtoResponse.Response.League.Standing.Away.toRankedLocalResponseLeagueStandingAway(): RankedCompetitionResponseLocal.Response.League.Standing.Away{
    return RankedCompetitionResponseLocal.Response.League.Standing.Away(
        draw = draw,
        goals = goals.toRankedLocalResponseLeagueStandingAwayGoals(),
        lose = lose,
        played = played,
        win = win
    )
}

private fun RankedCompetitionDtoResponse.Response.League.Standing.Away.Goals.toRankedLocalResponseLeagueStandingAwayGoals(): RankedCompetitionResponseLocal.Response.League.Standing.Away.Goals{
    return RankedCompetitionResponseLocal.Response.League.Standing.Away.Goals(
        against, forTeam
    )
}

private fun RankedCompetitionDtoResponse.Response.League.Standing.Home.toRankedLocalResponseLeagueStandingAll(): RankedCompetitionResponseLocal.Response.League.Standing.Home{
    return RankedCompetitionResponseLocal.Response.League.Standing.Home(
        draw = draw,
        goals = goals.toRankedLocalResponseLeagueStandingHomeGoals(),
        lose = lose,
        played = played,
        win = win
    )
}

private fun RankedCompetitionDtoResponse.Response.League.Standing.Home.Goals.toRankedLocalResponseLeagueStandingHomeGoals(): RankedCompetitionResponseLocal.Response.League.Standing.Home.Goals{
    return RankedCompetitionResponseLocal.Response.League.Standing.Home.Goals(
        against, forTeam
    )
}

private fun RankedCompetitionDtoResponse.Response.League.Standing.Team.toRankedLocalResponseLeagueStandingTeam(): RankedCompetitionResponseLocal.Response.League.Standing.Team{
    return RankedCompetitionResponseLocal.Response.League.Standing.Team(
        id, logo, name
    )
}



