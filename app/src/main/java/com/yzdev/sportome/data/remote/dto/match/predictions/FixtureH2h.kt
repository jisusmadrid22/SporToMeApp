package com.yzdev.sportome.data.remote.dto.match.predictions

data class PeriodsFixtureH2h(
    val first: Int?,
    val second: Int?
)

data class StatusFixtureH2h(
    val elapsed: Int?,
    val long: String?,
    val short: String?
)

data class VenueFixtureH2h(
    val city: String?,
    val id: Int?,
    val name: String?
)