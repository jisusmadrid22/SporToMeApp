package com.yzdev.sportome.common

import android.util.Log
import com.yzdev.sportome.R
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


fun timeToUnix(): Long {
    return System.currentTimeMillis() / 1000
}

fun getAllDateNumberOfWeek(): List<Int>{
    val now = Calendar.getInstance()

    val format = SimpleDateFormat("dd")

    val days = mutableListOf<Int>()
    val delta = -now[GregorianCalendar.DAY_OF_WEEK] + 1 //add 2 if your week start on monday

    now.add(Calendar.DAY_OF_MONTH, delta)
    for (i in 0..6) {
        days.add(format.format(now.time).toInt())
        now.add(Calendar.DAY_OF_MONTH, 1)
    }
    return days
}

fun getAllDateOfWeek(): List<String>{
    val now = Calendar.getInstance()

    val format = SimpleDateFormat("yyyy-MM-dd")

    val days = mutableListOf<String>()
    val delta = -now[GregorianCalendar.DAY_OF_WEEK] + 1 //add 2 if your week start on monday

    now.add(Calendar.DAY_OF_MONTH, delta)
    for (i in 0..6) {
        days.add(format.format(now.time))
        now.add(Calendar.DAY_OF_MONTH, 1)
    }

    //Log.e("date", days.toString())
    return days
}

fun getNameDayWeek(day: Int): String{
    return when(day){
        0-> AppResource.getString(R.string.sunday)
        1-> AppResource.getString(R.string.monday)
        2-> AppResource.getString(R.string.tuesday)
        3-> AppResource.getString(R.string.wednesday)
        4-> AppResource.getString(R.string.thursday)
        5-> AppResource.getString(R.string.friday)
        6-> AppResource.getString(R.string.saturday)
        else-> ""
    }
}

fun unixToDateTime(unix: Long): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    val netDate = Date(unix * 1000)
    return sdf.format(netDate)
}

fun getCurrentDay(): Int {
    val cal = Calendar.getInstance()

    return cal[Calendar.DAY_OF_MONTH]
}

fun getHourDifference(timeDifference: Long): Int{
    var time = timeDifference
    val hours = time / 3600
    time %= 3600
    val minutes = time / 60
    time %= 60
    val seconds = time
    return hours.toInt()
}

fun currentDayIsMonday(): Boolean{
    return getAllDateOfWeek().first() == unixToDateTime(timeToUnix())
}

fun dateToUnix(date: String): Long{
    val l = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val unix = l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond

    return unix
}

fun unixToDayWeek(unix: Long): String{
    val sdf = SimpleDateFormat("dd")
    val netDate = Date(unix * 1000)

    Log.e("ee", "day ${sdf.format(netDate)}")
    return sdf.format(netDate)
}