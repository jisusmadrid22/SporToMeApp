package com.yzdev.sportome.common

fun getAllSports(): List<Sport>{
    return listOf(
        Sport(
            id = 0,
            name = "Football"
        ),
        Sport(
            id = 1,
            name = "Baseball"
        ),
        Sport(
            id = 2,
            name = "Tennis"
        ),
        Sport(
            id = 3,
            name = "Basketball"
        )
    )
}

fun getAllCountry(): List<Country>{
    return listOf(
        Country(
            id = 0,
            name = "Spain",
            idSports = listOf(
                0, 2, 3
            )
        ),
        Country(
            id = 1,
            name = "Venezuela",
            idSports = listOf(
                0, 1, 3
            )
        ),
        Country(
            id = 2,
            name = "United State",
            idSports = listOf(
                0, 1
            )
        ),
        Country(
            id = 3,
            name = "Germany",
            idSports = listOf(
                0
            )
        )
    )
}

fun getCountryBySport(idSport: Int): List<Country>{
    val allCountry = getAllCountry()

    val filteredList = allCountry.filter {
        it.idSports.find { item-> item == idSport } != null
    }

    return filteredList
}

fun getAllLeagues(): List<League>{
    return listOf(
        League(
            id = 0,
            name = "La Liga",
            idCountry = 0,
            teams = listOf(
                Team(
                    id = 0,
                    name = "Real Madrid"
                ),
                Team(
                    id = 1,
                    name = "Barcelona"
                ),
                Team(
                    id = 2,
                    name = "Atletico de Madrid"
                ),
                Team(
                    id = 3,
                    name = "Osasuna"
                )
            )
        ),
        League(
            id = 1,
            name = "Primera division",
            idCountry = 1,
            teams = listOf(
                Team(
                    id = 4,
                    name = "Deportivo Táchira FC"
                ),
                Team(
                    id = 5,
                    name = "Academia Puerto Cabello"
                ),
                Team(
                    id = 6,
                    name = "Portuguesa FC"
                ),
                Team(
                    id = 7,
                    name = "AC Lara"
                )
            )
        )
    )
}

fun getLeaguesByCountry(idCountry: Int): List<League>{
    val allLeagues = getAllLeagues()

    val filteredList = allLeagues.filter {
        it.idCountry == idCountry
    }

    return filteredList
}

data class Sport(
    val id: Int,
    val name: String
)

data class Country(
    val id: Int,
    val name: String,
    val idSports: List<Int>
)

data class League(
    val id: Int,
    val name: String,
    val idCountry: Int,
    val teams: List<Team>
)

data class Team(
    val id: Int,
    val name: String
)

/** match*/

fun getTeamMatch(): List<TeamMatch>{
    return listOf(
        TeamMatch(
            stadium = "Nombre estadio",
            teams = listOf(
                TeamInfo(
                    nameTeam = "Team 1",
                    typeTeam = "Alfitrion",
                    goal = 1
                ),
                TeamInfo(
                    nameTeam = "Team 2",
                    typeTeam = "Invitado",
                    goal = 3
                )
            ),
            time = "56:25",
            dateTime = "09:00 Am"
        ),
        TeamMatch(
            stadium = "Nombre estadio",
            teams = listOf(
                TeamInfo(
                    nameTeam = "Team 1",
                    typeTeam = "Alfitrion",
                    goal = 0
                ),
                TeamInfo(
                    nameTeam = "Team 2",
                    typeTeam = "Invitado",
                    goal = 0
                )
            ),
            time = "90:00 +4",
            dateTime = "03:00 Pm"
        ),
        TeamMatch(
            stadium = "Nombre estadio",
            teams = listOf(
                TeamInfo(
                    nameTeam = "Team 1",
                    typeTeam = "Alfitrion",
                    goal = 1
                ),
                TeamInfo(
                    nameTeam = "Team 2",
                    typeTeam = "Invitado",
                    goal = 0
                )
            ),
            time = "32:00",
            dateTime = "04:00 Pm"
        ),
        TeamMatch(
            stadium = "Nombre estadio",
            teams = listOf(
                TeamInfo(
                    nameTeam = "Team 1",
                    typeTeam = "Alfitrion",
                    goal = 1
                ),
                TeamInfo(
                    nameTeam = "Team 2",
                    typeTeam = "Invitado",
                    goal = 0
                )
            ),
            time = "32:00",
            dateTime = "12:00 Pm"
        )
    )
}

data class TeamMatch(
    val stadium: String,
    val teams: List<TeamInfo>,
    val time: String,
    val dateTime: String
)

data class TeamInfo(
    val nameTeam: String,
    val typeTeam: String,
    val goal: Int
)

/** competition list*/

fun getListCompetition(): List<Competition>{
    return listOf(
        Competition(
            name = "Competicion 1",
            matches = getTeamMatch()
        ),
        Competition(
            name = "Competicion 2",
            matches = getTeamMatch()
        ),
        Competition(
            name = "Competicion 3",
            matches = getTeamMatch()
        ),
        Competition(
            name = "Competicion 4",
            matches = getTeamMatch()
        )
    )
}

data class Competition(
    val name: String,
    val matches: List<TeamMatch>
)

fun getAllLeaguesFavorites(): List<League>{
    return listOf(
        League(
            id = 0,
            name = "Liga 1",
            idCountry = 0,
            teams = listOf(
                Team(
                    id = 0,
                    name = "Real Madrid"
                ),
                Team(
                    id = 1,
                    name = "Barcelona"
                ),
                Team(
                    id = 2,
                    name = "Atletico de Madrid"
                ),
                Team(
                    id = 3,
                    name = "Osasuna"
                )
            )
        ),
        League(
            id = 1,
            name = "Liga 2",
            idCountry = 1,
            teams = listOf(
                Team(
                    id = 4,
                    name = "Deportivo Táchira FC"
                ),
                Team(
                    id = 5,
                    name = "Academia Puerto Cabello"
                ),
                Team(
                    id = 6,
                    name = "Portuguesa FC"
                ),
                Team(
                    id = 7,
                    name = "AC Lara"
                )
            )
        ),
        League(
            id = 1,
            name = "Copa 1",
            idCountry = 1,
            teams = listOf(
                Team(
                    id = 4,
                    name = "Deportivo Táchira FC"
                ),
                Team(
                    id = 5,
                    name = "Academia Puerto Cabello"
                ),
                Team(
                    id = 6,
                    name = "Portuguesa FC"
                ),
                Team(
                    id = 7,
                    name = "AC Lara"
                )
            )
        ),
        League(
            id = 1,
            name = "Copa 2",
            idCountry = 1,
            teams = listOf(
                Team(
                    id = 4,
                    name = "Deportivo Táchira FC"
                ),
                Team(
                    id = 5,
                    name = "Academia Puerto Cabello"
                ),
                Team(
                    id = 6,
                    name = "Portuguesa FC"
                ),
                Team(
                    id = 7,
                    name = "AC Lara"
                )
            )
        )
    )
}

fun getAllTeamsFavorites(): List<Team>{
    return listOf(
        Team(
            id = 0,
            name = "Team 1"
        ),
        Team(
            id = 0,
            name = "Team 2"
        ),
        Team(
            id = 0,
            name = "Team 3"
        ),
        Team(
            id = 0,
            name = "Team 4"
        ),
        Team(
            id = 0,
            name = "Team 5"
        )
    )
}