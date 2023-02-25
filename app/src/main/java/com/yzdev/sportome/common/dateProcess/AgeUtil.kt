package com.yzdev.sportome.common.dateProcess

import android.util.Log
import kotlin.math.abs

fun getDifferenceAge(
    ageParent: Int = 18, ageTest: Int
): Int{
    val difference = abs(ageTest - ageParent)
    Log.e("AgeDifference", "difference -> $difference")

    return difference
}