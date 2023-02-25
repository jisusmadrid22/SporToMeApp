package com.yzdev.sportome.common

import com.yzdev.sportome.presentation.Destination
import com.yzdev.sportome.presentation.screens.home.composables.DestinationHome

fun getListItemBottomApp(): List<DestinationHome>{
    return listOf(
        DestinationHome.TODAY_MATCH,
        DestinationHome.FAVORITES,
        DestinationHome.SEARCH
    )
}