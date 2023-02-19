package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.team.TeamStatsDtoResponse

data class TeamStatsLocalResponse(
    val response: Response,
) {
    data class Response(
        val clean_sheet: CleanSheet,
        val failed_to_score: FailedToScore,
        val fixtures: Fixtures,
        val form: String?,
        val goals: Goals,
        val league: League,
        val lineups: List<Lineup>,
        val penalty: Penalty,
        val team: Team
    ) {

        data class CleanSheet(
            val away: Int?,
            val home: Int?,
            val total: Int?
        )

        data class FailedToScore(
            val away: Int?,
            val home: Int?,
            val total: Int?
        )

        data class Fixtures(
            val draws: Draws,
            val loses: Loses,
            val played: Played,
            val wins: Wins
        ) {
            data class Draws(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )

            data class Loses(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )

            data class Played(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )

            data class Wins(
                val away: Int?,
                val home: Int?,
                val total: Int?
            )
        }

        data class Goals(
            val against: Against,
            val forTeam: For
        ) {
            data class Against(
                val total: Total
            ) {
                data class Total(
                    val away: Int?,
                    val home: Int?,
                    val total: Int?
                )
            }

            data class For(
                val total: Total
            ) {
                data class Total(
                    val away: Int?,
                    val home: Int?,
                    val total: Int?
                )
            }
        }

        data class League(
            val country: String?,
            val flag: String?,
            val id: Int?,
            val logo: String?,
            val name: String?,
            val season: Int?
        )

        data class Lineup(
            val formation: String?,
            val played: Int?
        )

        data class Penalty(
            val missed: Missed,
            val scored: Scored,
            val total: Int?
        ) {
            data class Missed(
                val percentage: String?,
                val total: Int?
            )

            data class Scored(
                val percentage: String?,
                val total: Int?
            )
        }

        data class Team(
            val id: Int?,
            val logo: String?,
            val name: String?
        )
    }
}

/** mapper*/

fun TeamStatsDtoResponse.toTeamStatsLocal(): TeamStatsLocalResponse{
    return TeamStatsLocalResponse(
        response = response.toTeamStatsLocalResponse()
    )
}

private fun TeamStatsDtoResponse.Response.toTeamStatsLocalResponse(): TeamStatsLocalResponse.Response{
    return TeamStatsLocalResponse.Response(
        clean_sheet = clean_sheet.toTeamStatsLocalResponseClean(),
        failed_to_score = failed_to_score.toTeamStatsLocalResponseFailedToScore(),
        fixtures = fixtures.toTeamStatsLocalResponseFixtures(),
        form = form,
        goals = goals.toTeamStatsLocalResponseGoals(),
        league = league.toTeamStatsLocalResponseLeague(),
        lineups = lineups.toTeamStatsLocalResponseLineup(),
        penalty = penalty.toTeamStatsLocalResponsePenalty(),
        team = team.toTeamStatsLocalResponseTeam()
    )
}

private fun TeamStatsDtoResponse.Response.CleanSheet.toTeamStatsLocalResponseClean(): TeamStatsLocalResponse.Response.CleanSheet{
    return TeamStatsLocalResponse.Response.CleanSheet(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.FailedToScore.toTeamStatsLocalResponseFailedToScore(): TeamStatsLocalResponse.Response.FailedToScore{
    return TeamStatsLocalResponse.Response.FailedToScore(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.Fixtures.toTeamStatsLocalResponseFixtures(): TeamStatsLocalResponse.Response.Fixtures{
    return TeamStatsLocalResponse.Response.Fixtures(
        draws = draws.toTeamStatsLocalResponseFixtureDraws(),
        loses = loses.toTeamStatsLocalResponseFixtureLoses(),
        played = played.toTeamStatsLocalResponseFixturePlayed(),
        wins = wins.toTeamStatsLocalResponseFixtureWins()
    )
}

private fun TeamStatsDtoResponse.Response.Fixtures.Draws.toTeamStatsLocalResponseFixtureDraws(): TeamStatsLocalResponse.Response.Fixtures.Draws{
    return TeamStatsLocalResponse.Response.Fixtures.Draws(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.Fixtures.Loses.toTeamStatsLocalResponseFixtureLoses(): TeamStatsLocalResponse.Response.Fixtures.Loses{
    return TeamStatsLocalResponse.Response.Fixtures.Loses(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.Fixtures.Played.toTeamStatsLocalResponseFixturePlayed(): TeamStatsLocalResponse.Response.Fixtures.Played{
    return TeamStatsLocalResponse.Response.Fixtures.Played(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.Fixtures.Wins.toTeamStatsLocalResponseFixtureWins(): TeamStatsLocalResponse.Response.Fixtures.Wins{
    return TeamStatsLocalResponse.Response.Fixtures.Wins(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.Goals.toTeamStatsLocalResponseGoals(): TeamStatsLocalResponse.Response.Goals{
    return TeamStatsLocalResponse.Response.Goals(
        against = against.toTeamStatsLocalResponseGoalAgainst(),
        forTeam = forTeam.toTeamStatsLocalResponseGoalFor()
    )
}

private fun TeamStatsDtoResponse.Response.Goals.Against.toTeamStatsLocalResponseGoalAgainst(): TeamStatsLocalResponse.Response.Goals.Against{
    return TeamStatsLocalResponse.Response.Goals.Against(
        total = total.toTeamStatsLocalResponseGoalAgainstTotal()
    )
}

private fun TeamStatsDtoResponse.Response.Goals.Against.Total.toTeamStatsLocalResponseGoalAgainstTotal(): TeamStatsLocalResponse.Response.Goals.Against.Total{
    return TeamStatsLocalResponse.Response.Goals.Against.Total(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.Goals.For.toTeamStatsLocalResponseGoalFor(): TeamStatsLocalResponse.Response.Goals.For{
    return TeamStatsLocalResponse.Response.Goals.For(
        total = total.toTeamStatsLocalResponseGoalForTotal()
    )
}

private fun TeamStatsDtoResponse.Response.Goals.For.Total.toTeamStatsLocalResponseGoalForTotal(): TeamStatsLocalResponse.Response.Goals.For.Total{
    return TeamStatsLocalResponse.Response.Goals.For.Total(
        away, home, total
    )
}

private fun TeamStatsDtoResponse.Response.League.toTeamStatsLocalResponseLeague(): TeamStatsLocalResponse.Response.League{
    return TeamStatsLocalResponse.Response.League(
        country, flag, id, logo, name, season
    )
}

private fun List<TeamStatsDtoResponse.Response.Lineup>.toTeamStatsLocalResponseLineup(): List<TeamStatsLocalResponse.Response.Lineup>{
    return this.map {
        TeamStatsLocalResponse.Response.Lineup(
            it.formation, it.played
        )
    }
}

private fun TeamStatsDtoResponse.Response.Penalty.toTeamStatsLocalResponsePenalty(): TeamStatsLocalResponse.Response.Penalty{
    return TeamStatsLocalResponse.Response.Penalty(
        missed = missed.toTeamStatsLocalResponsePenaltyMissed(),
        scored = scored.toTeamStatsLocalResponsePenaltyScored(),
        total = total
    )
}

private fun TeamStatsDtoResponse.Response.Penalty.Missed.toTeamStatsLocalResponsePenaltyMissed(): TeamStatsLocalResponse.Response.Penalty.Missed{
    return TeamStatsLocalResponse.Response.Penalty.Missed(
        percentage, total
    )
}

private fun TeamStatsDtoResponse.Response.Penalty.Scored.toTeamStatsLocalResponsePenaltyScored(): TeamStatsLocalResponse.Response.Penalty.Scored{
    return TeamStatsLocalResponse.Response.Penalty.Scored(
        percentage, total
    )
}

private fun TeamStatsDtoResponse.Response.Team.toTeamStatsLocalResponseTeam(): TeamStatsLocalResponse.Response.Team{
    return TeamStatsLocalResponse.Response.Team(
        id, logo, name
    )
}