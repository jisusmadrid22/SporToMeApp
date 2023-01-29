package com.yzdev.sportome.data.remote.dto.match.predictions

data class FixtureH2h(
    val date: String?,
    val id: Int?,
    val periods: PeriodsFixtureH2h?,
    val referee: String?,
    val status: StatusFixtureH2h?,
    val timestamp: Int?,
    val timezone: String?,
    val venue: VenueFixtureH2h?
)

data class GoalsH2h(
    val away: Int?,
    val home: Int?
)

data class LeagueH2h(
    val country: String?,
    val flag: String?,
    val id: Int?,
    val logo: String?,
    val name: String?,
    val round: String?,
    val season: Int?
)

data class ScoreH2h(
    val extratime: ExtratimeScoreH2h?,
    val fulltime: FulltimeScoreH2h?,
    val halftime: HalftimeScoreH2h?,
    val penalty: PenaltyScoreH2h?
)

data class TeamsH2h(
    val away: AwayTeamsH2h?,
    val home: HomeTeamsH2h?
)