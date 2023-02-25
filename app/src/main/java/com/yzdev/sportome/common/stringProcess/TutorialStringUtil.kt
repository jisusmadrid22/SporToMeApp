package com.yzdev.sportome.common

import com.yzdev.sportome.R

fun titleTutorialByStep(
    numberStep: Int
): String{
    return when(numberStep){
        1-> "$numberStep. ${AppResource.getString(R.string.sportFavorite)}"
        2-> "$numberStep. ${AppResource.getString(R.string.countryFavorite)}"
        3-> "$numberStep. ${AppResource.getString(R.string.competitionFavorite)}"
        4-> "$numberStep. ${AppResource.getString(R.string.teamFavorite)}"
        else -> ""
    }
}

fun labelTextFieldByStep(
    numberStep: Int
): String{
    return when(numberStep){
        1-> AppResource.getString(R.string.searchSport)
        2-> AppResource.getString(R.string.searchCountry)
        3-> AppResource.getString(R.string.searchCompetition)
        4-> AppResource.getString(R.string.searchTeam)
        else -> ""
    }
}

fun labelTextFieldByStepSearch(
    numberStep: Int
): String{
    return when(numberStep){
        1-> AppResource.getString(R.string.searchCountry)
        2-> AppResource.getString(R.string.searchCompetition)
        else -> ""
    }
}