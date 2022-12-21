package com.yzdev.sportome.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource

enum class Destination(val screenRoute: String, val title: String, val icon: ImageVector){
    HOME(
        screenRoute = "SPTM_SCREEN:HOME_1",
        title = AppResource.getString(R.string.homeTitle),
        icon = Icons.Rounded.Home
    ),
    ON_BOARDING(
        screenRoute = "SPTM_SCREEN:ON_BOARDING_1",
        title = AppResource.getString(R.string.onBoardingTitle),
        icon = Icons.Rounded.List
    ),
    TUTORIAL(
        screenRoute = "SPTM_SCREEN:TUTORIAL_1",
        title = AppResource.getString(R.string.tutorialTitle),
        icon = Icons.Rounded.List
    )
}