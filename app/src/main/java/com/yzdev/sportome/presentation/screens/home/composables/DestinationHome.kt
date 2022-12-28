package com.yzdev.sportome.presentation.screens.home.composables

import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource

enum class DestinationHome(val screenRoute: String, val title: String, val iconRes: Int) {
    TODAY_MATCH(
        screenRoute = "SPTM_SCREEN:TODAY_MATCH_1",
        title = AppResource.getString(R.string.todayMatchTitle),
        iconRes = R.drawable.ic_home
    ),
    FAVORITES(
        screenRoute = "SPTM_SCREEN:FAVORITES_1",
        title = AppResource.getString(R.string.favorites),
        iconRes = R.drawable.ic_heart
    ),
    SEARCH(
        screenRoute = "SPTM_SCREEN:SEARCH_1",
        title = AppResource.getString(R.string.search),
        iconRes = R.drawable.ic_search
    ),
}