package com.yzdev.sportome.common

import android.util.Log
import com.yzdev.sportome.R
import java.text.SimpleDateFormat
import java.util.*


fun timeToUnix(): Long {
    return System.currentTimeMillis() / 1000
}

fun getAllDateOfWeek(): List<Int>{
    val now = Calendar.getInstance()

    val format = SimpleDateFormat("dd")

    val days = mutableListOf<Int>()
    val delta = -now[GregorianCalendar.DAY_OF_WEEK] + 2 //add 2 if your week start on monday

    now.add(Calendar.DAY_OF_MONTH, delta)
    for (i in 0..6) {
        days.add(format.format(now.time).toInt())
        now.add(Calendar.DAY_OF_MONTH, 1)
    }

    //Log.e("date", days.toString())
    return days
}

fun getNameDayWeek(day: Int): String{
    return when(day){
        0-> AppResource.getString(R.string.monday)
        1-> AppResource.getString(R.string.tuesday)
        2-> AppResource.getString(R.string.wednesday)
        3-> AppResource.getString(R.string.thursday)
        4-> AppResource.getString(R.string.friday)
        5-> AppResource.getString(R.string.saturday)
        6-> AppResource.getString(R.string.sunday)
        else-> ""
    }
}

fun getCurrentDay(): Int {
    val cal = Calendar.getInstance()

    return cal[Calendar.DAY_OF_MONTH]
}