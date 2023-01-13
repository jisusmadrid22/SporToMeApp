package com.yzdev.sportome.domain.model

import com.yzdev.sportome.data.remote.dto.match.DetailMatchDtoResponse

data class DetailMatchResponse(
    val events: List<Event>,
    val fixture: Fixture,
    val goals: Goals,
    val league: League,
    val lineups: List<Lineup>,
    val players: List<Player>,
    val score: Score,
    val statistics: List<Statistic>,
    val teams: Teams
){
    data class Event(
        val assist: Assist,
        val comments: String?,
        val detail: String,
        val player: Player,
        val team: Team,
        val time: Time,
        val type: String
    ) {
        data class Assist(
            val id: Int?,
            val name: String?
        )

        data class Player(
            val id: Int,
            val name: String
        )

        data class Team(
            val id: Int,
            val logo: String,
            val name: String
        )

        data class Time(
            val elapsed: Int,
            val extra: Int?
        )
    }

    data class Fixture(
        val date: String,
        val id: Int,
        val periods: Periods,
        val referee: String,
        val status: Status,
        val timestamp: Int,
        val timezone: String,
        val venue: Venue
    ) {
        data class Periods(
            val first: Int,
            val second: Int
        )

        data class Status(
            val elapsed: Int,
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
        val away: Int,
        val home: Int
    )

    data class League(
        val country: String,
        val flag: String,
        val id: Int,
        val logo: String,
        val name: String,
        val round: String,
        val season: Int
    )

    data class Lineup(
        val coach: Coach,
        val formation: String,
        val startXI: List<StartXI>,
        val substitutes: List<Substitute>,
        val team: Team
    ) {
        data class Coach(
            val id: Int,
            val name: String,
            val photo: String
        )

        data class StartXI(
            val player: Player
        ) {
            data class Player(
                val grid: String,
                val id: Int,
                val name: String,
                val number: Int,
                val pos: String
            )
        }

        data class Substitute(
            val player: Player
        ) {
            data class Player(
                val grid: String?,
                val id: Int,
                val name: String,
                val number: Int,
                val pos: String
            )
        }

        data class Team(
            val colors: Colors,
            val id: Int,
            val logo: String,
            val name: String
        ) {
            data class Colors(
                val goalkeeper: Goalkeeper,
                val player: Player
            ) {
                data class Goalkeeper(
                    val border: String,
                    val number: String,
                    val primary: String
                )

                data class Player(
                    val border: String,
                    val number: String,
                    val primary: String
                )
            }
        }
    }

    data class Player(
        val players: List<Player>,
        val team: Team
    ) {
        data class Player(
            val player: Player,
            val statistics: List<Statistic>
        ) {
            data class Player(
                val id: Int,
                val name: String,
                val photo: String
            )

            data class Statistic(
                val cards: Cards,
                val dribbles: Dribbles,
                val duels: Duels,
                val fouls: Fouls,
                val games: Games,
                val goals: Goals,
                val offsides: Int?,
                val passes: Passes,
                val penalty: Penalty,
                val shots: Shots,
                val tackles: Tackles
            ) {
                data class Cards(
                    val red: Int,
                    val yellow: Int
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
                    val captain: Boolean,
                    val minutes: Int?,
                    val number: Int,
                    val position: String,
                    val rating: String?,
                    val substitute: Boolean
                )

                data class Goals(
                    val assists: Int?,
                    val conceded: Int,
                    val saves: Int?,
                    val total: Int?
                )

                data class Passes(
                    val accuracy: String?,
                    val key: Int?,
                    val total: Int?
                )

                data class Penalty(
                    val commited: Int?,
                    val missed: Int,
                    val saved: Int?,
                    val scored: Int,
                    val won: Any?
                )

                data class Shots(
                    val on: Int?,
                    val total: Int?
                )

                data class Tackles(
                    val blocks: Int?,
                    val interceptions: Int?,
                    val total: Int?
                )
            }
        }

        data class Team(
            val id: Int,
            val logo: String,
            val name: String,
            val update: String
        )
    }

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

    data class Statistic(
        val statistics: List<Statistic>,
        val team: Team
    ) {
        data class Statistic(
            val type: String,
            val value: String?
        )

        data class Team(
            val id: Int,
            val logo: String,
            val name: String
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

/** mapper*/
fun DetailMatchDtoResponse.toDetailMatch(): DetailMatchResponse{
    return DetailMatchResponse(
        events = response.first().events.toDetailMatchListEvent(),
        fixture = response.first().fixture.toDetailMatchFixture(),
        goals = response.first().goals.toDetailMatchGoals(),
        league = response.first().league.toDetailMatchLeague(),
        lineups = response.first().lineups.toDetailMatchListLineups(),
        players = response.first().players.toDetailMatchListPlayers(),
        score = response.first().score.toDetailMatchScore(),
        statistics = response.first().statistics.toDetailMatchStats(),
        teams = response.first().teams.toDetailMatchTeam()
    )
}

private fun DetailMatchDtoResponse.Response.Teams.toDetailMatchTeam(): DetailMatchResponse.Teams{
    return DetailMatchResponse.Teams(
        away = away.toDetailMatchTeamAway(),
        home = home.toDetailMatchTeamHome()
    )
}

private fun DetailMatchDtoResponse.Response.Teams.Home.toDetailMatchTeamHome(): DetailMatchResponse.Teams.Home{
    return DetailMatchResponse.Teams.Home(
        id, logo, name, winner
    )
}

private fun DetailMatchDtoResponse.Response.Teams.Away.toDetailMatchTeamAway(): DetailMatchResponse.Teams.Away{
    return DetailMatchResponse.Teams.Away(
        id, logo, name, winner
    )
}

private fun List<DetailMatchDtoResponse.Response.Statistic>.toDetailMatchStats(): List<DetailMatchResponse.Statistic>{
    return this.map {
        DetailMatchResponse.Statistic(
            statistics = it.statistics.toDetailMatchStatsStats(),
            team = it.team.toDetailMatchStatsTeam()
        )
    }
}

private fun List<DetailMatchDtoResponse.Response.Statistic.Statistic>.toDetailMatchStatsStats(): List<DetailMatchResponse.Statistic.Statistic>{
    return this.map {
        DetailMatchResponse.Statistic.Statistic(
            it.type, it.value.toString()
        )
    }
}

private fun DetailMatchDtoResponse.Response.Statistic.Team.toDetailMatchStatsTeam(): DetailMatchResponse.Statistic.Team{
    return DetailMatchResponse.Statistic.Team(
        id, logo, name
    )
}

private fun DetailMatchDtoResponse.Response.Score.toDetailMatchScore(): DetailMatchResponse.Score{
    return DetailMatchResponse.Score(
        extratime = extratime.toDetailMatchScoreExtratime(),
        fulltime = fulltime.toDetailMatchScoreFulltime(),
        halftime = halftime.toDetailMatchScoreHalftime(),
        penalty = penalty.toDetailMatchScorePenalty()
    )
}

private fun DetailMatchDtoResponse.Response.Score.Extratime.toDetailMatchScoreExtratime(): DetailMatchResponse.Score.Extratime{
    return DetailMatchResponse.Score.Extratime(
        away, home
    )
}

private fun DetailMatchDtoResponse.Response.Score.Fulltime.toDetailMatchScoreFulltime(): DetailMatchResponse.Score.Fulltime{
    return DetailMatchResponse.Score.Fulltime(
        away, home
    )
}

private fun DetailMatchDtoResponse.Response.Score.Halftime.toDetailMatchScoreHalftime(): DetailMatchResponse.Score.Halftime{
    return DetailMatchResponse.Score.Halftime(
        away, home
    )
}

private fun DetailMatchDtoResponse.Response.Score.Penalty.toDetailMatchScorePenalty(): DetailMatchResponse.Score.Penalty{
    return DetailMatchResponse.Score.Penalty(
        away, home
    )
}

private fun List<DetailMatchDtoResponse.Response.Player>.toDetailMatchListPlayers(): List<DetailMatchResponse.Player>{
    return this.map {
        DetailMatchResponse.Player(
            players = it.players.toDetailMatchPlayerPLayer(),
            team = it.team.toDetailMatchPlayerTeam()
        )
    }
}

private fun DetailMatchDtoResponse.Response.Player.Team.toDetailMatchPlayerTeam(): DetailMatchResponse.Player.Team{
    return DetailMatchResponse.Player.Team(
        id, logo, name, update
    )
}

private fun List<DetailMatchDtoResponse.Response.Player.Player>.toDetailMatchPlayerPLayer(): List<DetailMatchResponse.Player.Player>{
    return this.map {
        DetailMatchResponse.Player.Player(
            player = it.player.toDetailMatchPlayerPLayerPlayer(),
            statistics = it.statistics.toDetailMatchPlayerPLayerStats()
        )
    }
}

private fun DetailMatchDtoResponse.Response.Player.Player.Player.toDetailMatchPlayerPLayerPlayer(): DetailMatchResponse.Player.Player.Player{
    return DetailMatchResponse.Player.Player.Player(
        id, name, photo
    )
}

private fun List<DetailMatchDtoResponse.Response.Player.Player.Statistic>.toDetailMatchPlayerPLayerStats(): List<DetailMatchResponse.Player.Player.Statistic>{
    return this.map {
        DetailMatchResponse.Player.Player.Statistic(
            cards = it.cards.toDetailMatchPlayerPLayerStatsCard(),
            dribbles = it.dribbles.toDetailMatchPlayerPLayerStatsdribbles(),
            duels = it.duels.toDetailMatchPlayerPLayerStatsDuels(),
            fouls = it.fouls.toDetailMatchPlayerPLayerStatsFouls(),
            games = it.games.toDetailMatchPlayerPLayerStatsGames(),
            goals = it.goals.toDetailMatchPlayerPLayerStatsGoals(),
            offsides = it.offsides,
            passes = it.passes.toDetailMatchPlayerPLayerStatsPasses(),
            penalty = it.penalty.toDetailMatchPlayerPLayerStatsPenalty(),
            shots = it.shots.toDetailMatchPlayerPLayerStatsShots(),
            tackles = it.tackles.toDetailMatchPlayerPLayerStatsTackles()
        )
    }
}

private fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Cards.toDetailMatchPlayerPLayerStatsCard(): DetailMatchResponse.Player.Player.Statistic.Cards{
    return DetailMatchResponse.Player.Player.Statistic.Cards(
        red, yellow
    )
}

private fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Dribbles.toDetailMatchPlayerPLayerStatsdribbles(): DetailMatchResponse.Player.Player.Statistic.Dribbles{
    return DetailMatchResponse.Player.Player.Statistic.Dribbles(
        attempts, past, success
    )
}

private fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Duels.toDetailMatchPlayerPLayerStatsDuels(): DetailMatchResponse.Player.Player.Statistic.Duels{
    return DetailMatchResponse.Player.Player.Statistic.Duels(
        total, won
    )
}

private fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Fouls.toDetailMatchPlayerPLayerStatsFouls(): DetailMatchResponse.Player.Player.Statistic.Fouls{
    return DetailMatchResponse.Player.Player.Statistic.Fouls(
        committed, drawn
    )
}

private fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Games.toDetailMatchPlayerPLayerStatsGames(): DetailMatchResponse.Player.Player.Statistic.Games{
    return DetailMatchResponse.Player.Player.Statistic.Games(
        captain, minutes, number, position, rating, substitute
    )
}

fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Goals.toDetailMatchPlayerPLayerStatsGoals(): DetailMatchResponse.Player.Player.Statistic.Goals{
    return DetailMatchResponse.Player.Player.Statistic.Goals(
        assists, conceded, saves, total
    )
}

fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Passes.toDetailMatchPlayerPLayerStatsPasses(): DetailMatchResponse.Player.Player.Statistic.Passes{
    return DetailMatchResponse.Player.Player.Statistic.Passes(
        accuracy, key, total
    )
}

fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Penalty.toDetailMatchPlayerPLayerStatsPenalty(): DetailMatchResponse.Player.Player.Statistic.Penalty{
    return DetailMatchResponse.Player.Player.Statistic.Penalty(
        commited, missed, saved, scored, won
    )
}

fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Shots.toDetailMatchPlayerPLayerStatsShots(): DetailMatchResponse.Player.Player.Statistic.Shots{
    return DetailMatchResponse.Player.Player.Statistic.Shots(
        on, total
    )
}

fun DetailMatchDtoResponse.Response.Player.Player.Statistic.Tackles.toDetailMatchPlayerPLayerStatsTackles(): DetailMatchResponse.Player.Player.Statistic.Tackles{
    return DetailMatchResponse.Player.Player.Statistic.Tackles(
        blocks, interceptions, total
    )
}

private fun List<DetailMatchDtoResponse.Response.Lineup>.toDetailMatchListLineups(): List<DetailMatchResponse.Lineup>{
    return this.map {
        DetailMatchResponse.Lineup(
            coach = it.coach.toDetailMatchLineupCoach(),
            formation = it.formation,
            startXI = it.startXI.toDetailMatchLineupListStartXI(),
            substitutes = it.substitutes.toDetailMatchLineupListSubstitute(),
            team = it.team.toDetailMatchLineupTeams()
        )
    }
}

private fun DetailMatchDtoResponse.Response.Lineup.Team.toDetailMatchLineupTeams(): DetailMatchResponse.Lineup.Team{
    return DetailMatchResponse.Lineup.Team(
        colors = colors.toDetailMatchLineupTeamsColors(),
        id, logo, name
    )
}

private fun DetailMatchDtoResponse.Response.Lineup.Team.Colors.toDetailMatchLineupTeamsColors(): DetailMatchResponse.Lineup.Team.Colors{
    return DetailMatchResponse.Lineup.Team.Colors(
        goalkeeper = goalkeeper.toDetailMatchLineupTeamsColorsGoalKeeper(),
        player = player.toDetailMatchLineupTeamsColorsPlayer()
    )
}

private fun DetailMatchDtoResponse.Response.Lineup.Team.Colors.Goalkeeper.toDetailMatchLineupTeamsColorsGoalKeeper(): DetailMatchResponse.Lineup.Team.Colors.Goalkeeper{
    return DetailMatchResponse.Lineup.Team.Colors.Goalkeeper(
        border, number, primary
    )
}

private fun DetailMatchDtoResponse.Response.Lineup.Team.Colors.Player.toDetailMatchLineupTeamsColorsPlayer(): DetailMatchResponse.Lineup.Team.Colors.Player{
    return DetailMatchResponse.Lineup.Team.Colors.Player(
        border, number, primary
    )
}

private fun List<DetailMatchDtoResponse.Response.Lineup.Substitute>.toDetailMatchLineupListSubstitute(): List<DetailMatchResponse.Lineup.Substitute>{
    return this.map {
        DetailMatchResponse.Lineup.Substitute(
            player = it.player.toDetailMatchLineupSubstitutePlayer()
        )
    }
}

private fun DetailMatchDtoResponse.Response.Lineup.Substitute.Player.toDetailMatchLineupSubstitutePlayer(): DetailMatchResponse.Lineup.Substitute.Player{
    return DetailMatchResponse.Lineup.Substitute.Player(
        grid, id, name, number, pos
    )
}

private fun List<DetailMatchDtoResponse.Response.Lineup.StartXI>.toDetailMatchLineupListStartXI(): List<DetailMatchResponse.Lineup.StartXI>{
    return this.map {
        DetailMatchResponse.Lineup.StartXI(
            player = it.player.toDetailMatchLineupStartXIPlayer()
        )
    }
}

private fun DetailMatchDtoResponse.Response.Lineup.StartXI.Player.toDetailMatchLineupStartXIPlayer(): DetailMatchResponse.Lineup.StartXI.Player{
    return DetailMatchResponse.Lineup.StartXI.Player(
        grid, id, name, number, pos
    )
}

private fun DetailMatchDtoResponse.Response.Lineup.Coach.toDetailMatchLineupCoach(): DetailMatchResponse.Lineup.Coach{
    return DetailMatchResponse.Lineup.Coach(
        id, name, photo
    )
}

private fun DetailMatchDtoResponse.Response.League.toDetailMatchLeague(): DetailMatchResponse.League{
    return DetailMatchResponse.League(
        country, flag, id, logo, name, round, season
    )
}

private fun DetailMatchDtoResponse.Response.Goals.toDetailMatchGoals(): DetailMatchResponse.Goals{
    return DetailMatchResponse.Goals(
        away, home
    )
}

private fun DetailMatchDtoResponse.Response.Fixture.toDetailMatchFixture(): DetailMatchResponse.Fixture{
    return DetailMatchResponse.Fixture(
        date = date,
        id = id,
        periods = periods.toDetailMatchFixturePeriods(),
        referee = referee,
        status = status.toDetailMatchFixtureStatus(),
        timestamp = timestamp,
        timezone = timezone,
        venue = venue.toDetailMatchFixtureVenue()
    )
}

private fun DetailMatchDtoResponse.Response.Fixture.Periods.toDetailMatchFixturePeriods(): DetailMatchResponse.Fixture.Periods{
    return DetailMatchResponse.Fixture.Periods(
        first, second
    )
}

private fun DetailMatchDtoResponse.Response.Fixture.Status.toDetailMatchFixtureStatus(): DetailMatchResponse.Fixture.Status{
    return DetailMatchResponse.Fixture.Status(
        elapsed, long, short
    )
}

private fun DetailMatchDtoResponse.Response.Fixture.Venue.toDetailMatchFixtureVenue(): DetailMatchResponse.Fixture.Venue{
    return DetailMatchResponse.Fixture.Venue(
        city, id, name
    )
}

private fun List<DetailMatchDtoResponse.Response.Event>.toDetailMatchListEvent(): List<DetailMatchResponse.Event>{
    return this.map {
        DetailMatchResponse.Event(
            assist = it.assist.toDetailMatchEventAssist(),
            comments = it.comments,
            detail = it.detail,
            player = it.player.toDetailMatchEventPlayer(),
            team = it.team.toDetailMatchEventTeam(),
            time = it.time.toDetailMatchEventTime(),
            type = it.type
        )
    }
}

private fun DetailMatchDtoResponse.Response.Event.Assist.toDetailMatchEventAssist(): DetailMatchResponse.Event.Assist{
    return DetailMatchResponse.Event.Assist(
        id, name
    )
}

private fun DetailMatchDtoResponse.Response.Event.Player.toDetailMatchEventPlayer(): DetailMatchResponse.Event.Player{
    return DetailMatchResponse.Event.Player(
        id, name
    )
}

private fun DetailMatchDtoResponse.Response.Event.Team.toDetailMatchEventTeam(): DetailMatchResponse.Event.Team{
    return DetailMatchResponse.Event.Team(
        id, logo, name
    )
}

private fun DetailMatchDtoResponse.Response.Event.Time.toDetailMatchEventTime(): DetailMatchResponse.Event.Time{
    return DetailMatchResponse.Event.Time(
        elapsed, extra
    )
}

