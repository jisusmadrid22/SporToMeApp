package com.yzdev.sportome.domain.model

import com.google.gson.annotations.SerializedName
import com.yzdev.sportome.data.remote.dto.player.InfoPlayerDto

data class InfoPlayerResponse(
    val response: List<Response>,
) {
    data class Response(
        val player: Player?,
        val statistics: List<Statistic>
    ) {
        data class Player(
            val age: Int,
            val birth: Birth,
            val firstname: String,
            val height: String,
            val id: Int,
            val injured: Boolean,
            val lastname: String,
            val name: String,
            val nationality: String,
            val photo: String,
            val weight: String
        ) {
            data class Birth(
                val country: String,
                val date: String,
                val place: String
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
                val number: Any?,
                val position: String?,
                val rating: String?
            )

            data class Goals(
                val assists: Int?,
                val conceded: Int?,
                val saves: Any?,
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
                val commited: Any?,
                val missed: Int?,
                val saved: Any?,
                val scored: Int?,
                val won: Any?
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

fun InfoPlayerDto.toInfoPlayerLocal(): InfoPlayerResponse{
    return InfoPlayerResponse(
        response = response.toInfoPlayerLocalResponse()
    )
}

private fun List<InfoPlayerDto.Response>.toInfoPlayerLocalResponse(): List<InfoPlayerResponse.Response>{
    return this.map {
        InfoPlayerResponse.Response(
            player = it.player.toInfoPlayerLocalResponsePlayer(),
            statistics = it.statistics.toInfoPlayerLocalResponseStats()
        )
    }
}

private fun List<InfoPlayerDto.Response.Statistic>.toInfoPlayerLocalResponseStats(): List<InfoPlayerResponse.Response.Statistic>{
    return this.map {
        InfoPlayerResponse.Response.Statistic(
            cards = it.cards.toInfoPlayerLocalResponseStatsCard(),
            dribbles = it.dribbles.toInfoPlayerLocalResponseStatsDribbles(),
            duels = it.duels.toInfoPlayerLocalResponseStatsDuels(),
            fouls = it.fouls.toInfoPlayerLocalResponseStatsFouls(),
            games = it.games.toInfoPlayerLocalResponseStatsGames(),
            goals = it.goals.toInfoPlayerLocalResponseStatsGoals(),
            league = it.league.toInfoPlayerLocalResponseStatsLeague(),
            passes = it.passes.toInfoPlayerLocalResponseStatsPasses(),
            penalty = it.penalty.toInfoPlayerLocalResponseStatsPenalty(),
            shots = it.shots.toInfoPlayerLocalResponseStatsShots(),
            substitutes = it.substitutes.toInfoPlayerLocalResponseStatsSubstitutes(),
            tackles = it.tackles.toInfoPlayerLocalResponseStatsTackles(),
            team = it.team.toInfoPlayerLocalResponseStatsTeam()
        )
    }
}

private fun InfoPlayerDto.Response.Statistic.Cards.toInfoPlayerLocalResponseStatsCard(): InfoPlayerResponse.Response.Statistic.Cards{
    return InfoPlayerResponse.Response.Statistic.Cards(
        red, yellow, yellowred
    )
}

private fun InfoPlayerDto.Response.Statistic.Dribbles.toInfoPlayerLocalResponseStatsDribbles(): InfoPlayerResponse.Response.Statistic.Dribbles{
    return InfoPlayerResponse.Response.Statistic.Dribbles(
        attempts, past, success
    )
}

private fun InfoPlayerDto.Response.Statistic.Duels.toInfoPlayerLocalResponseStatsDuels(): InfoPlayerResponse.Response.Statistic.Duels{
    return InfoPlayerResponse.Response.Statistic.Duels(
        total, won
    )
}

private fun InfoPlayerDto.Response.Statistic.Fouls.toInfoPlayerLocalResponseStatsFouls(): InfoPlayerResponse.Response.Statistic.Fouls{
    return InfoPlayerResponse.Response.Statistic.Fouls(
        committed, drawn
    )
}

private fun InfoPlayerDto.Response.Statistic.Games.toInfoPlayerLocalResponseStatsGames(): InfoPlayerResponse.Response.Statistic.Games{
    return InfoPlayerResponse.Response.Statistic.Games(
        appearences, captain, lineups, minutes, number, position, rating
    )
}

private fun InfoPlayerDto.Response.Statistic.Penalty.toInfoPlayerLocalResponseStatsPenalty(): InfoPlayerResponse.Response.Statistic.Penalty{
    return InfoPlayerResponse.Response.Statistic.Penalty(
        commited, missed, saved, scored, won
    )
}

private fun InfoPlayerDto.Response.Statistic.League.toInfoPlayerLocalResponseStatsLeague(): InfoPlayerResponse.Response.Statistic.League{
    return InfoPlayerResponse.Response.Statistic.League(
        country, flag, id, logo, name, season
    )
}

private fun InfoPlayerDto.Response.Statistic.Passes.toInfoPlayerLocalResponseStatsPasses(): InfoPlayerResponse.Response.Statistic.Passes{
    return InfoPlayerResponse.Response.Statistic.Passes(
        accuracy, key, total
    )
}

private fun InfoPlayerDto.Response.Statistic.Goals.toInfoPlayerLocalResponseStatsGoals(): InfoPlayerResponse.Response.Statistic.Goals{
    return InfoPlayerResponse.Response.Statistic.Goals(
        assists, conceded, saves, total
    )
}

private fun InfoPlayerDto.Response.Statistic.Shots.toInfoPlayerLocalResponseStatsShots(): InfoPlayerResponse.Response.Statistic.Shots{
    return InfoPlayerResponse.Response.Statistic.Shots(
        on, total
    )
}

private fun InfoPlayerDto.Response.Statistic.Substitutes.toInfoPlayerLocalResponseStatsSubstitutes(): InfoPlayerResponse.Response.Statistic.Substitutes{
    return InfoPlayerResponse.Response.Statistic.Substitutes(
        bench, inTeam, outTeam
    )
}

private fun InfoPlayerDto.Response.Statistic.Tackles.toInfoPlayerLocalResponseStatsTackles(): InfoPlayerResponse.Response.Statistic.Tackles{
    return InfoPlayerResponse.Response.Statistic.Tackles(
        blocks, interceptions, total
    )
}

private fun InfoPlayerDto.Response.Statistic.Team.toInfoPlayerLocalResponseStatsTeam(): InfoPlayerResponse.Response.Statistic.Team{
    return InfoPlayerResponse.Response.Statistic.Team(
        id, logo, name
    )
}

private fun InfoPlayerDto.Response.Player.toInfoPlayerLocalResponsePlayer(): InfoPlayerResponse.Response.Player{
    return InfoPlayerResponse.Response.Player(
        age = age,
        birth = birth.toInfoPlayerLocalResponsePlayerBirth(),
        firstname, height, id, injured, lastname, name, nationality, photo, weight
    )
}

private fun InfoPlayerDto.Response.Player.Birth.toInfoPlayerLocalResponsePlayerBirth(): InfoPlayerResponse.Response.Player.Birth{
    return InfoPlayerResponse.Response.Player.Birth(
        country, date, place
    )
}