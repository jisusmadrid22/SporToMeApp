package com.yzdev.sportome.data.remote.dto.match

import com.google.gson.annotations.SerializedName

data class DetailMatchDtoResponse(
    val errors: List<Any>,
    @SerializedName("get") val get: String,
    val paging: Paging,
    val parameters: Parameters,
    val response: List<Response>,
    val results: Int
) {
    data class Paging(
        val current: Int,
        val total: Int
    )

    data class Parameters(
        val id: String
    )

    data class Response(
        val events: List<Event>,
        val fixture: Fixture,
        val goals: Goals,
        val league: League,
        val lineups: List<Lineup>,
        val players: List<Player>,
        val score: Score,
        val statistics: List<Statistic>,
        val teams: Teams
    ) {
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
                val value: Any?
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
}