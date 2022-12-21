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