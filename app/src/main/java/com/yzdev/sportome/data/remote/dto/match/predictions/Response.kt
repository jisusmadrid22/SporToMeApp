package com.yzdev.sportome.data.remote.dto.match.predictions

import com.google.gson.annotations.SerializedName

data class Comparison(
    val att: Att?,
    val def: Def?,
    val form: Form?,
    val goals: Goals?,
    val h2h: H2hComparison?,
    val poisson_distribution: PoissonDistribution?,
    val total: Total?
)

data class H2h(
    val fixture: FixtureH2h?,
    val goals: GoalsH2h?,
    val league: LeagueH2h?,
    val score: ScoreH2h?,
    val teams: TeamsH2h?
)

data class League(
    val country: String?,
    val flag: String?,
    val id: Int?,
    val logo: String?,
    val name: String?,
    val season: Int?
)

data class Predictions(
    val advice: String?,
    val goals: GoalsPredictions?,
    val percent: PercentPredictions?,
    val under_over: Any?,
    val win_or_draw: Boolean?,
    val winner: WinnerPredictions?
)

data class GoalsPredictions(
    val away: String?,
    val home: String?
)

data class PercentPredictions(
    val away: String?,
    val draw: String?,
    val home: String?
)

data class WinnerPredictions(
    val comment: String?,
    val id: Int?,
    val name: String?
)

/***********************************/

data class Teams(
    val away: Away?,
    val home: Home?
) {
    data class Away(
        val id: Int?,
        val last_5: Last5?,
        val league: League?,
        val logo: String?,
        val name: String?
    ) {
        data class Last5(
            val att: String?,
            val def: String?,
            val form: String?,
            val goals: Goals?
        ) {
            data class Goals(
                val against: Against?,
                @SerializedName("for") val forGoals: For?
            ) {
                data class Against(
                    val average: String?,
                    val total: Int?
                )

                data class For(
                    val average: String?,
                    val total: Int?
                )
            }
        }

        data class League(
            val biggest: Biggest?,
            val cards: Cards?,
            val clean_sheet: CleanSheet?,
            val failed_to_score: FailedToScore?,
            val fixtures: Fixtures?,
            val form: String?,
            val goals: Goals?,
            val lineups: List<Lineup?>?,
            val penalty: Penalty?
        ) {
            data class Biggest(
                val goals: Goals?,
                val loses: Loses?,
                val streak: Streak?,
                val wins: Wins?
            ) {
                data class Goals(
                    val against: Against?,
                    @SerializedName("for") val forGoals: For?
                ) {
                    data class Against(
                        val away: Int?,
                        val home: Int?
                    )

                    data class For(
                        val away: Int?,
                        val home: Int?
                    )
                }

                data class Loses(
                    val away: String?,
                    val home: String?
                )

                data class Streak(
                    val draws: Int?,
                    val loses: Int?,
                    val wins: Int?
                )

                data class Wins(
                    val away: String?,
                    val home: String?
                )
            }

            data class Cards(
                val red: Red?,
                val yellow: Yellow?
            ) {
                data class Red(
                    @SerializedName("0-15") val t015: X015?,
                    @SerializedName("106-120") val t106_120: X106120?,
                    @SerializedName("16-30") val t16_30: X1630?,
                    @SerializedName("31-45") val t31_45: X3145?,
                    @SerializedName("46-60") val t46_60: X4660?,
                    @SerializedName("61-75") val t61_75: X6175?,
                    @SerializedName("76-90") val t76_90: X7690?,
                    @SerializedName("91-105") val t91_105: X91105?
                ) {
                    data class X015(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X106120(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X1630(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X3145(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X4660(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X6175(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X7690(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X91105(
                        val percentage: Any?,
                        val total: Any?
                    )
                }

                data class Yellow(
                    @SerializedName("0-15") val t015: X015?,
                    @SerializedName("106-120") val t106_120: X106120?,
                    @SerializedName("16-30") val t16_30: X1630?,
                    @SerializedName("31-45") val t31_45: X3145?,
                    @SerializedName("46-60") val t46_60: X4660?,
                    @SerializedName("61-75") val t61_75: X6175?,
                    @SerializedName("76-90") val t76_90: X7690?,
                    @SerializedName("91-105") val t91_105: X91105?
                ) {
                    data class X015(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X106120(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X1630(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X3145(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X4660(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X6175(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X7690(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X91105(
                        val percentage: String?,
                        val total: Int?
                    )
                }
            }

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
                val draws: Draws?,
                val loses: Loses?,
                val played: Played?,
                val wins: Wins?
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
                val against: Against?,
                @SerializedName("for") val forGoals: For?
            ) {
                data class Against(
                    val average: Average?,
                    val minute: Minute?,
                    val total: Total?
                ) {
                    data class Average(
                        val away: String?,
                        val home: String?,
                        val total: String?
                    )

                    data class Minute(
                        @SerializedName("0-15") val t015: X015?,
                        @SerializedName("106-120") val t106_120: X106120?,
                        @SerializedName("16-30") val t16_30: X1630?,
                        @SerializedName("31-45") val t31_45: X3145?,
                        @SerializedName("46-60") val t46_60: X4660?,
                        @SerializedName("61-75") val t61_75: X6175?,
                        @SerializedName("76-90") val t76_90: X7690?,
                        @SerializedName("91-105") val t91_105: X91105?
                    ) {
                        data class X015(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X106120(
                            val percentage: Any?,
                            val total: Any?
                        )

                        data class X1630(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X3145(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X4660(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X6175(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X7690(
                            val percentage: Any?,
                            val total: Any?
                        )

                        data class X91105(
                            val percentage: String?,
                            val total: Int?
                        )
                    }

                    data class Total(
                        val away: Int?,
                        val home: Int?,
                        val total: Int?
                    )
                }

                data class For(
                    val average: Average?,
                    val minute: Minute?,
                    val total: Total?
                ) {
                    data class Average(
                        val away: String?,
                        val home: String?,
                        val total: String?
                    )

                    data class Minute(
                        @SerializedName("0-15") val t015: X015?,
                        @SerializedName("106-120") val t106_120: X106120?,
                        @SerializedName("16-30") val t16_30: X1630?,
                        @SerializedName("31-45") val t31_45: X3145?,
                        @SerializedName("46-60") val t46_60: X4660?,
                        @SerializedName("61-75") val t61_75: X6175?,
                        @SerializedName("76-90") val t76_90: X7690?,
                        @SerializedName("91-105") val t91_105: X91105?
                    ) {
                        data class X015(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X106120(
                            val percentage: Any?,
                            val total: Any?
                        )

                        data class X1630(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X3145(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X4660(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X6175(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X7690(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X91105(
                            val percentage: Any?,
                            val total: Any?
                        )
                    }

                    data class Total(
                        val away: Int?,
                        val home: Int?,
                        val total: Int?
                    )
                }
            }

            data class Lineup(
                val formation: String?,
                val played: Int?
            )

            data class Penalty(
                val missed: Missed?,
                val scored: Scored?,
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
        }
    }

    data class Home(
        val id: Int?,
        val last_5: Last5?,
        val league: League?,
        val logo: String?,
        val name: String?
    ) {
        data class Last5(
            val att: String?,
            val def: String?,
            val form: String?,
            val goals: Goals?
        ) {
            data class Goals(
                val against: Against?,
                @SerializedName("for") val forGoals: For?
            ) {
                data class Against(
                    val average: String?,
                    val total: Int?
                )

                data class For(
                    val average: String?,
                    val total: Int?
                )
            }
        }

        data class League(
            val biggest: Biggest?,
            val cards: Cards?,
            val clean_sheet: CleanSheet?,
            val failed_to_score: FailedToScore?,
            val fixtures: Fixtures?,
            val form: String?,
            val goals: Goals?,
            val lineups: List<Lineup?>?,
            val penalty: Penalty?
        ) {
            data class Biggest(
                val goals: Goals?,
                val loses: Loses?,
                val streak: Streak?,
                val wins: Wins?
            ) {
                data class Goals(
                    val against: Against?,
                    @SerializedName("for") val forGoals: For?
                ) {
                    data class Against(
                        val away: Int?,
                        val home: Int?
                    )

                    data class For(
                        val away: Int?,
                        val home: Int?
                    )
                }

                data class Loses(
                    val away: String?,
                    val home: String?
                )

                data class Streak(
                    val draws: Int?,
                    val loses: Int?,
                    val wins: Int?
                )

                data class Wins(
                    val away: String?,
                    val home: String?
                )
            }

            data class Cards(
                val red: Red?,
                val yellow: Yellow?
            ) {
                data class Red(
                    @SerializedName("0-15") val t015: X015?,
                    @SerializedName("106-120") val t106_120: X106120?,
                    @SerializedName("16-30") val t16_30: X1630?,
                    @SerializedName("31-45") val t31_45: X3145?,
                    @SerializedName("46-60") val t46_60: X4660?,
                    @SerializedName("61-75") val t61_75: X6175?,
                    @SerializedName("76-90") val t76_90: X7690?,
                    @SerializedName("91-105") val t91_105: X91105?
                ) {
                    data class X015(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X106120(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X1630(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X3145(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X4660(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X6175(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X7690(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X91105(
                        val percentage: Any?,
                        val total: Any?
                    )
                }

                data class Yellow(
                    @SerializedName("0-15") val t015: X015?,
                    @SerializedName("106-120") val t106_120: X106120?,
                    @SerializedName("16-30") val t16_30: X1630?,
                    @SerializedName("31-45") val t31_45: X3145?,
                    @SerializedName("46-60") val t46_60: X4660?,
                    @SerializedName("61-75") val t61_75: X6175?,
                    @SerializedName("76-90") val t76_90: X7690?,
                    @SerializedName("91-105") val t91_105: X91105?
                ) {
                    data class X015(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X106120(
                        val percentage: Any?,
                        val total: Any?
                    )

                    data class X1630(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X3145(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X4660(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X6175(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X7690(
                        val percentage: String?,
                        val total: Int?
                    )

                    data class X91105(
                        val percentage: String?,
                        val total: Int?
                    )
                }
            }

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
                val draws: Draws?,
                val loses: Loses?,
                val played: Played?,
                val wins: Wins?
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
                val against: Against?,
                @SerializedName("for") val forGoals: For?
            ) {
                data class Against(
                    val average: Average?,
                    val minute: Minute?,
                    val total: Total?
                ) {
                    data class Average(
                        val away: String?,
                        val home: String?,
                        val total: String?
                    )

                    data class Minute(
                        @SerializedName("0-15") val t015: X015?,
                        @SerializedName("106-120") val t106_120: X106120?,
                        @SerializedName("16-30") val t16_30: X1630?,
                        @SerializedName("31-45") val t31_45: X3145?,
                        @SerializedName("46-60") val t46_60: X4660?,
                        @SerializedName("61-75") val t61_75: X6175?,
                        @SerializedName("76-90") val t76_90: X7690?,
                        @SerializedName("91-105") val t91_105: X91105?
                    ) {
                        data class X015(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X106120(
                            val percentage: Any?,
                            val total: Any?
                        )

                        data class X1630(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X3145(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X4660(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X6175(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X7690(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X91105(
                            val percentage: String?,
                            val total: Int?
                        )
                    }

                    data class Total(
                        val away: Int?,
                        val home: Int?,
                        val total: Int?
                    )
                }

                data class For(
                    val average: Average?,
                    val minute: Minute?,
                    val total: Total?
                ) {
                    data class Average(
                        val away: String?,
                        val home: String?,
                        val total: String?
                    )

                    data class Minute(
                        @SerializedName("0-15") val t015: X015?,
                        @SerializedName("106-120") val t106_120: X106120?,
                        @SerializedName("16-30") val t16_30: X1630?,
                        @SerializedName("31-45") val t31_45: X3145?,
                        @SerializedName("46-60") val t46_60: X4660?,
                        @SerializedName("61-75") val t61_75: X6175?,
                        @SerializedName("76-90") val t76_90: X7690?,
                        @SerializedName("91-105") val t91_105: X91105?
                    ) {
                        data class X015(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X106120(
                            val percentage: Any?,
                            val total: Any?
                        )

                        data class X1630(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X3145(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X4660(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X6175(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X7690(
                            val percentage: String?,
                            val total: Int?
                        )

                        data class X91105(
                            val percentage: Any?,
                            val total: Any?
                        )
                    }

                    data class Total(
                        val away: Int?,
                        val home: Int?,
                        val total: Int?
                    )
                }
            }

            data class Lineup(
                val formation: String?,
                val played: Int?
            )

            data class Penalty(
                val missed: Missed?,
                val scored: Scored?,
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
        }
    }
}