package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.competition.TopAssistLeagueDtoResponse

data class TopAssistLeagueLocalResponse(
    val response: List<Response>
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
                val past: Int?,
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

fun TopAssistLeagueDtoResponse.topTopAssistLeagueLocal(): TopAssistLeagueLocalResponse{
    return TopAssistLeagueLocalResponse(
        response = response.topTopAssistLeagueLocalResponse()
    )
}

fun List<TopAssistLeagueDtoResponse.Response>.topTopAssistLeagueLocalResponse(): List<TopAssistLeagueLocalResponse.Response>{
    return this.map {
        TopAssistLeagueLocalResponse.Response(
            player = it.player.topTopAssistLeagueLocalResponsePlayer(),
            statistics = it.statistics.topTopAssistLeagueLocalResponseStatistic()
        )
    }
}

fun TopAssistLeagueDtoResponse.Response.Player.topTopAssistLeagueLocalResponsePlayer(): TopAssistLeagueLocalResponse.Response.Player{
    return TopAssistLeagueLocalResponse.Response.Player(
        age = age,
        birth = birth.topTopAssistLeagueLocalResponsePlayerBirth(),
        firstname, height, id, injured, lastname, name, nationality, photo, weight
    )
}

fun TopAssistLeagueDtoResponse.Response.Player.Birth.topTopAssistLeagueLocalResponsePlayerBirth(): TopAssistLeagueLocalResponse.Response.Player.Birth{
    return TopAssistLeagueLocalResponse.Response.Player.Birth(
        country, date, place
    )
}

fun List<TopAssistLeagueDtoResponse.Response.Statistic>.topTopAssistLeagueLocalResponseStatistic(): List<TopAssistLeagueLocalResponse.Response.Statistic>{
    return this.map {
        TopAssistLeagueLocalResponse.Response.Statistic(
            cards = it.cards.topTopAssistLeagueLocalResponseStatsCards(),
            dribbles = it.dribbles.topTopAssistLeagueLocalResponseStatsDribbles(),
            duels = it.duels.topTopAssistLeagueLocalResponseStatsDuels(),
            fouls = it.fouls.topTopAssistLeagueLocalResponseStatsFouls(),
            games = it.games.topTopAssistLeagueLocalResponseStatsGames(),
            goals = it.goals.topTopAssistLeagueLocalResponseStatsGoals(),
            league = it.league.topTopAssistLeagueLocalResponseStatsLeague(),
            passes = it.passes.topTopAssistLeagueLocalResponseStatsPasses(),
            penalty = it.penalty.topTopAssistLeagueLocalResponseStatsPenalty(),
            shots = it.shots.topTopAssistLeagueLocalResponseStatsShots(),
            substitutes = it.substitutes.topTopAssistLeagueLocalResponseStatsSubs(),
            tackles = it.tackles.topTopAssistLeagueLocalResponseStatsTackles(),
            team = it.team.topTopAssistLeagueLocalResponseStatsTeam()
        )
    }
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Cards.topTopAssistLeagueLocalResponseStatsCards(): TopAssistLeagueLocalResponse.Response.Statistic.Cards{
    return TopAssistLeagueLocalResponse.Response.Statistic.Cards(
        red, yellow, yellowred
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Dribbles.topTopAssistLeagueLocalResponseStatsDribbles(): TopAssistLeagueLocalResponse.Response.Statistic.Dribbles{
    return TopAssistLeagueLocalResponse.Response.Statistic.Dribbles(
        attempts, past, success
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Duels.topTopAssistLeagueLocalResponseStatsDuels(): TopAssistLeagueLocalResponse.Response.Statistic.Duels{
    return TopAssistLeagueLocalResponse.Response.Statistic.Duels(
        total, won
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Fouls.topTopAssistLeagueLocalResponseStatsFouls(): TopAssistLeagueLocalResponse.Response.Statistic.Fouls{
    return TopAssistLeagueLocalResponse.Response.Statistic.Fouls(
        committed, drawn
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Games.topTopAssistLeagueLocalResponseStatsGames(): TopAssistLeagueLocalResponse.Response.Statistic.Games{
    return TopAssistLeagueLocalResponse.Response.Statistic.Games(
        appearences, captain, lineups, minutes, number, position, rating
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Goals.topTopAssistLeagueLocalResponseStatsGoals(): TopAssistLeagueLocalResponse.Response.Statistic.Goals{
    return TopAssistLeagueLocalResponse.Response.Statistic.Goals(
        assists, conceded, saves, total
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.League.topTopAssistLeagueLocalResponseStatsLeague(): TopAssistLeagueLocalResponse.Response.Statistic.League{
    return TopAssistLeagueLocalResponse.Response.Statistic.League(
        country, flag, id, logo, name, season
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Passes.topTopAssistLeagueLocalResponseStatsPasses(): TopAssistLeagueLocalResponse.Response.Statistic.Passes{
    return TopAssistLeagueLocalResponse.Response.Statistic.Passes(
        accuracy, key, total
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Penalty.topTopAssistLeagueLocalResponseStatsPenalty(): TopAssistLeagueLocalResponse.Response.Statistic.Penalty{
    return TopAssistLeagueLocalResponse.Response.Statistic.Penalty(
        commited, missed, saved, scored, won
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Shots.topTopAssistLeagueLocalResponseStatsShots(): TopAssistLeagueLocalResponse.Response.Statistic.Shots{
    return TopAssistLeagueLocalResponse.Response.Statistic.Shots(
        on, total
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Substitutes.topTopAssistLeagueLocalResponseStatsSubs(): TopAssistLeagueLocalResponse.Response.Statistic.Substitutes{
    return TopAssistLeagueLocalResponse.Response.Statistic.Substitutes(
        bench, inTeam, outTeam
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Tackles.topTopAssistLeagueLocalResponseStatsTackles(): TopAssistLeagueLocalResponse.Response.Statistic.Tackles{
    return TopAssistLeagueLocalResponse.Response.Statistic.Tackles(
        blocks, interceptions, total
    )
}

fun TopAssistLeagueDtoResponse.Response.Statistic.Team.topTopAssistLeagueLocalResponseStatsTeam(): TopAssistLeagueLocalResponse.Response.Statistic.Team{
    return TopAssistLeagueLocalResponse.Response.Statistic.Team(
        id, logo, name
    )
}