package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.competition.TopScoresLeagueDtoResponse

data class TopScoresLeagueLocalResponse(
    val response: List<Response>,
) {
    data class Response(
        val player: Player,
        val statistics: List<Statistic>
    ) {
        data class Player(
            val age: Int?,
            val birth: Birth,
            val firstname: String?,
            val height: String?,
            val id: Int?,
            val injured: Boolean?,
            val lastname: String?,
            val name: String?,
            val nationality: String?,
            val photo: String?,
            val weight: String?
        ) {
            data class Birth(
                val country: String?,
                val date: String?,
                val place: String?
            )
        }

        data class Statistic(
            val cards: Cards,
            val dribbles: Dribbles,
            val duels: Duels,
            val fouls: Fouls,
            val games: Games,
            val goals: Goals,
            val league: League,
            val passes: Passes,
            val penalty: Penalty,
            val shots: Shots,
            val substitutes: Substitutes,
            val tackles: Tackles,
            val team: Team
        ) {
            data class Cards(
                val red: Int?,
                val yellow: Int?,
                val yellowred: Int?
            )

            data class Dribbles(
                val attempts: Int?,
                val past: Any?,
                val success: Int?
            )

            data class Duels(
                val total: Int?,
                val won: Int?
            )

            data class Fouls(
                val committed: Int?,
                val drawn: Int?
            )

            data class Games(
                val appearences: Int?,
                val captain: Boolean?,
                val lineups: Int?,
                val minutes: Int?,
                val number: Int?,
                val position: String?,
                val rating: String?
            )

            data class Goals(
                val assists: Int?,
                val conceded: Int?,
                val saves: Int?,
                val total: Int?
            )

            data class League(
                val country: String?,
                val flag: String?,
                val id: Int?,
                val logo: String?,
                val name: String?,
                val season: Int?
            )

            data class Passes(
                val accuracy: Int?,
                val key: Int?,
                val total: Int?
            )

            data class Penalty(
                val commited: Int?,
                val missed: Int?,
                val saved: Int?,
                val scored: Int?,
                val won: Int?
            )

            data class Shots(
                val on: Int?,
                val total: Int?
            )

            data class Substitutes(
                val bench: Int?,
                val inTeam: Int?,
                val outTeam: Int?
            )

            data class Tackles(
                val blocks: Int?,
                val interceptions: Int?,
                val total: Int?
            )

            data class Team(
                val id: Int?,
                val logo: String?,
                val name: String?
            )
        }
    }
}

/** mapper*/

fun TopScoresLeagueDtoResponse.toTopScoresLeagueLocal(): TopScoresLeagueLocalResponse{
    return TopScoresLeagueLocalResponse(
        response = response.toTopScoresLeagueLocalResponse()
    )
}

fun List<TopScoresLeagueDtoResponse.Response>.toTopScoresLeagueLocalResponse(): List<TopScoresLeagueLocalResponse.Response>{
    return this.map {
        TopScoresLeagueLocalResponse.Response(
            player = it.player.toTopScoresLeagueLocalResponsePlayer(),
            statistics = it.statistics.toTopScoresLeagueLocalResponseStatistic()
        )
    }
}

fun TopScoresLeagueDtoResponse.Response.Player.toTopScoresLeagueLocalResponsePlayer(): TopScoresLeagueLocalResponse.Response.Player{
    return TopScoresLeagueLocalResponse.Response.Player(
        age = age,
        birth = birth.toTopScoresLeagueLocalResponsePlayerBirth(),
        firstname, height, id, injured, lastname, name, nationality, photo, weight
    )
}

fun TopScoresLeagueDtoResponse.Response.Player.Birth.toTopScoresLeagueLocalResponsePlayerBirth(): TopScoresLeagueLocalResponse.Response.Player.Birth{
    return TopScoresLeagueLocalResponse.Response.Player.Birth(
        country, date, place
    )
}

fun List<TopScoresLeagueDtoResponse.Response.Statistic>.toTopScoresLeagueLocalResponseStatistic(): List<TopScoresLeagueLocalResponse.Response.Statistic>{
    return this.map {
        TopScoresLeagueLocalResponse.Response.Statistic(
            cards = it.cards.toTopScoresLeagueLocalResponseStatsCards(),
            dribbles = it.dribbles.toTopScoresLeagueLocalResponseStatsDribbles(),
            duels = it.duels.toTopScoresLeagueLocalResponseStatsDuels(),
            fouls = it.fouls.toTopScoresLeagueLocalResponseStatsFouls(),
            games = it.games.toTopScoresLeagueLocalResponseStatsGames(),
            goals = it.goals.toTopScoresLeagueLocalResponseStatsGoals(),
            league = it.league.toTopScoresLeagueLocalResponseStatsLeague(),
            passes = it.passes.toTopScoresLeagueLocalResponseStatsPasses(),
            penalty = it.penalty.toTopScoresLeagueLocalResponseStatsPenalty(),
            shots = it.shots.toTopScoresLeagueLocalResponseStatsShots(),
            substitutes = it.substitutes.toTopScoresLeagueLocalResponseStatsSubs(),
            tackles = it.tackles.toTopScoresLeagueLocalResponseStatsTackles(),
            team = it.team.toTopScoresLeagueLocalResponseStatsTeam()
        )
    }
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Cards.toTopScoresLeagueLocalResponseStatsCards(): TopScoresLeagueLocalResponse.Response.Statistic.Cards{
    return TopScoresLeagueLocalResponse.Response.Statistic.Cards(
        red, yellow, yellowred
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Dribbles.toTopScoresLeagueLocalResponseStatsDribbles(): TopScoresLeagueLocalResponse.Response.Statistic.Dribbles{
    return TopScoresLeagueLocalResponse.Response.Statistic.Dribbles(
        attempts, past, success
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Duels.toTopScoresLeagueLocalResponseStatsDuels(): TopScoresLeagueLocalResponse.Response.Statistic.Duels{
    return TopScoresLeagueLocalResponse.Response.Statistic.Duels(
        total, won
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Fouls.toTopScoresLeagueLocalResponseStatsFouls(): TopScoresLeagueLocalResponse.Response.Statistic.Fouls{
    return TopScoresLeagueLocalResponse.Response.Statistic.Fouls(
        committed, drawn
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Games.toTopScoresLeagueLocalResponseStatsGames(): TopScoresLeagueLocalResponse.Response.Statistic.Games{
    return TopScoresLeagueLocalResponse.Response.Statistic.Games(
        appearences, captain, lineups, minutes, number, position, rating
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Goals.toTopScoresLeagueLocalResponseStatsGoals(): TopScoresLeagueLocalResponse.Response.Statistic.Goals{
    return TopScoresLeagueLocalResponse.Response.Statistic.Goals(
        assists, conceded, saves, total
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.League.toTopScoresLeagueLocalResponseStatsLeague(): TopScoresLeagueLocalResponse.Response.Statistic.League{
    return TopScoresLeagueLocalResponse.Response.Statistic.League(
        country, flag, id, logo, name, season
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Passes.toTopScoresLeagueLocalResponseStatsPasses(): TopScoresLeagueLocalResponse.Response.Statistic.Passes{
    return TopScoresLeagueLocalResponse.Response.Statistic.Passes(
        accuracy, key, total
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Penalty.toTopScoresLeagueLocalResponseStatsPenalty(): TopScoresLeagueLocalResponse.Response.Statistic.Penalty{
    return TopScoresLeagueLocalResponse.Response.Statistic.Penalty(
        commited, missed, saved, scored, won
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Shots.toTopScoresLeagueLocalResponseStatsShots(): TopScoresLeagueLocalResponse.Response.Statistic.Shots{
    return TopScoresLeagueLocalResponse.Response.Statistic.Shots(
        on, total
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Substitutes.toTopScoresLeagueLocalResponseStatsSubs(): TopScoresLeagueLocalResponse.Response.Statistic.Substitutes{
    return TopScoresLeagueLocalResponse.Response.Statistic.Substitutes(
        bench, inTeam, outTeam
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Tackles.toTopScoresLeagueLocalResponseStatsTackles(): TopScoresLeagueLocalResponse.Response.Statistic.Tackles{
    return TopScoresLeagueLocalResponse.Response.Statistic.Tackles(
        blocks, interceptions, total
    )
}

fun TopScoresLeagueDtoResponse.Response.Statistic.Team.toTopScoresLeagueLocalResponseStatsTeam(): TopScoresLeagueLocalResponse.Response.Statistic.Team{
    return TopScoresLeagueLocalResponse.Response.Statistic.Team(
        id, logo, name
    )
}

