package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.match.MatchesForThisWeekDtoResponse

data class MatchesResponseLocal(
    val fixture: Fixture,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams
) {
    data class Fixture(
        val date: String,
        val id: Int,
        val periods: Periods,
        val referee: String?,
        val status: Status,
        val timestamp: Long,
        val timezone: String,
        val venue: Venue
    ) {
        data class Periods(
            val first: Long?,
            val second: Long?
        )

        data class Status(
            val elapsed: Int?,
            val long: String,
            val short: String
        )

        data class Venue(
            val city: String,
            val id: Int,
            val name: String
        )
    }

    data class Goals(
        val away: Int?,
        val home: Int?
    )

    data class League(
        val country: String,
        val flag: String?,
        val id: Int,
        val logo: String,
        val name: String,
        val round: String,
        val season: Int
    )

    data class Score(
        val extratime: Extratime,
        val fulltime: Fulltime,
        val halftime: Halftime,
        val penalty: Penalty
    ) {
        data class Extratime(
            val away: Int?,
            val home: Int?
        )

        data class Fulltime(
            val away: Int?,
            val home: Int?
        )

        data class Halftime(
            val away: Int?,
            val home: Int?
        )

        data class Penalty(
            val away: Int?,
            val home: Int?
        )
    }

    data class Teams(
        val away: Away,
        val home: Home
    ) {
        data class Away(
            val id: Int,
            val logo: String,
            val name: String,
            val winner: Boolean?
        )

        data class Home(
            val id: Int,
            val logo: String,
            val name: String,
            val winner: Boolean?
        )
    }
}

fun MatchesForThisWeekDtoResponse.toListMatchesResponseLocal(): List<MatchesResponseLocal>{
    return this.response.map {
        MatchesResponseLocal(
            fixture = it.fixture.toMatchesResponseLocalFixture(),
            goals = it.goals.toMatchesResponseLocalGoals(),
            league = it.league.toMatchesResponseLocalLeague(),
            score = it.score.toMatchesResponseLocalScore(),
            teams = it.teams.toMatchesResponseLocalTeams()
        )
    }
}

/** mapper*/
private fun MatchesForThisWeekDtoResponse.Response.Fixture.toMatchesResponseLocalFixture(): MatchesResponseLocal.Fixture{
    return MatchesResponseLocal.Fixture(
        date = date,
        id = id,
        periods = periods.toMatchesResponseLocalFixturePeriods(),
        referee = referee,
        status = status.toMatchesResponseLocalFixtureStatus(),
        timestamp = timestamp,
        timezone = timezone,
        venue = venue.toMatchesResponseLocalFixtureVenue()
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Fixture.Periods.toMatchesResponseLocalFixturePeriods(): MatchesResponseLocal.Fixture.Periods{
    return MatchesResponseLocal.Fixture.Periods(
        first, second
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Fixture.Status.toMatchesResponseLocalFixtureStatus(): MatchesResponseLocal.Fixture.Status{
    return MatchesResponseLocal.Fixture.Status(
        elapsed, long, short
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Fixture.Venue.toMatchesResponseLocalFixtureVenue(): MatchesResponseLocal.Fixture.Venue{
    return MatchesResponseLocal.Fixture.Venue(
        city, id, name
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Goals.toMatchesResponseLocalGoals(): MatchesResponseLocal.Goals{
    return MatchesResponseLocal.Goals(
        away, home
    )
}

private fun MatchesForThisWeekDtoResponse.Response.League.toMatchesResponseLocalLeague(): MatchesResponseLocal.League{
    return MatchesResponseLocal.League(
        country, flag, id, logo, name, round, season
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Score.toMatchesResponseLocalScore(): MatchesResponseLocal.Score{
    return MatchesResponseLocal.Score(
        extratime = extratime.toMatchesResponseLocalScoreExtraTime(),
        fulltime = fulltime.toMatchesResponseLocalScoreFullTime(),
        halftime = halftime.toMatchesResponseLocalScoreHalfTime(),
        penalty = penalty.toMatchesResponseLocalScorePenalty()
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Score.Extratime.toMatchesResponseLocalScoreExtraTime(): MatchesResponseLocal.Score.Extratime{
    return MatchesResponseLocal.Score.Extratime(
        away, home
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Score.Fulltime.toMatchesResponseLocalScoreFullTime(): MatchesResponseLocal.Score.Fulltime{
    return MatchesResponseLocal.Score.Fulltime(
        away, home
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Score.Halftime.toMatchesResponseLocalScoreHalfTime(): MatchesResponseLocal.Score.Halftime{
    return MatchesResponseLocal.Score.Halftime(
        away, home
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Score.Penalty.toMatchesResponseLocalScorePenalty(): MatchesResponseLocal.Score.Penalty{
    return MatchesResponseLocal.Score.Penalty(
        away, home
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Teams.toMatchesResponseLocalTeams(): MatchesResponseLocal.Teams{
    return MatchesResponseLocal.Teams(
        away = away.toMatchesResponseLocalTeamsAway(),
        home = home.toMatchesResponseLocalTeamsHome()
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Teams.Home.toMatchesResponseLocalTeamsHome(): MatchesResponseLocal.Teams.Home{
    return MatchesResponseLocal.Teams.Home(
        id, logo, name, winner
    )
}

private fun MatchesForThisWeekDtoResponse.Response.Teams.Away.toMatchesResponseLocalTeamsAway(): MatchesResponseLocal.Teams.Away{
    return MatchesResponseLocal.Teams.Away(
        id, logo, name, winner
    )
}
