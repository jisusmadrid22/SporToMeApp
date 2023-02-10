package com.yzdev.sportome.common.listProcess

import com.yzdev.sportome.common.timeToUnix
import com.yzdev.sportome.common.unixToMonthYear
import com.yzdev.sportome.domain.model.LocalSeasonPlayer

fun getAllSeasonPlayerBySeasonMonth(
    listSeasonPlayer: List<LocalSeasonPlayer>
): List<LocalSeasonPlayer>{
    val monthNumber = unixToMonthYear(timeToUnix())

    return if ((monthNumber >= 1) and (monthNumber <= 7)){
        //season from year past
        listSeasonPlayer.dropLast(1)
    }else {
        //season from this year
        listSeasonPlayer
    }
}