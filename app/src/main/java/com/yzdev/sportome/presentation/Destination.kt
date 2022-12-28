package com.yzdev.sportome.presentation

import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource

enum class Destination(val screenRoute: String, val title: String, val iconRes: Int){
    HOME(
        screenRoute = "SPTM_SCREEN:HOME_1",
        title = AppResource.getString(R.string.homeTitle),
        iconRes = R.drawable.ic_home
    ),
    ON_BOARDING(
        screenRoute = "SPTM_SCREEN:ON_BOARDING_1",
        title = AppResource.getString(R.string.onBoardingTitle),
        iconRes = R.drawable.ic_home  //whatever this icon
    ),
    TUTORIAL(
        screenRoute = "SPTM_SCREEN:TUTORIAL_1",
        title = AppResource.getString(R.string.tutorialTitle),
        iconRes = R.drawable.ic_home  //whatever this icon
    ),
    SPORT_SELECTOR(
        screenRoute = "SPTM_SCREEN:SPORT_SELECTOR_1",
        title = AppResource.getString(R.string.sportSelector),
        iconRes = R.drawable.ic_sync
    ),
    LOGOUT(
        screenRoute = "SPTM_SCREEN:LOGOUT_1",
        title = AppResource.getString(R.string.closeApp),
        iconRes = R.drawable.ic_logout
    )
}