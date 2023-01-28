package com.yzdev.sportome.common

fun getFirstAndLastName(fullName: String): Pair<String, String>{
    val parts  = fullName.split(" ").toMutableList()
    val firstName = parts.firstOrNull()
    parts.removeAt(0)
    val lastName = parts.joinToString(" ")

    return Pair(firstName ?: "", lastName ?: "")
}