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
    ABOUT_US(
        screenRoute = "SPTM_SCREEN:ABOUT_US_1",
        title = AppResource.getString(R.string.about_us),
        iconRes = R.drawable.ic_info
    ),
    LOGOUT(
        screenRoute = "SPTM_SCREEN:LOGOUT_1",
        title = AppResource.getString(R.string.closeApp),
        iconRes = R.drawable.ic_logout
    ),
    DETAIL_MATCH(
        screenRoute = "SPTM_SCREEN:DETAIL_MATCH_1",
        title = AppResource.getString(R.string.detailMatch),
        iconRes = R.drawable.ic_info
    ),
    DETAIL_PLAYER(
        screenRoute = "SPTM_SCREEN:DETAIL_PLAYER_1",
        title = AppResource.getString(R.string.detailPlayer),
        iconRes = R.drawable.ic_info
    ),
    DETAIL_TEAM(
        screenRoute = "SPTM_SCREEN:DETAIL_TEAM_1",
        title = AppResource.getString(R.string.detailTeam),
        iconRes = R.drawable.ic_info
    )
}