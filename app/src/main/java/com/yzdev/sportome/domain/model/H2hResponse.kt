package com.yzdev.sportome.domain.model

import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.data.remote.dto.match.h2hResponseDto.*

data class H2hResponse(
    val fixture: Fixture,
    val goals: Goals,
    val league: League,
    val score: Score,
    val teams: Teams
)

/** mapper*/

fun H2hResponseDto.toListH2hResponse(): List<H2hResponse>{
    return this.response.filter { it.fixture.timestamp <= timeToUnix() }.sortedBy { it.fixture.timestamp }.reversed().map {
        H2hResponse(
            fixture = it.fixture,
            goals = it.goals,
            league = it.league,
            score = it.score,
            teams = it.teams
        )
    }
}
