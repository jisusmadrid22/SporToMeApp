package com.yzdev.sportome.common

import com.yzdev.sportome.presentation.Destination

/**
 * Esta funcion crea todas las opciones del menu drawer, para agregar mas opciones se debe añadir en eta funcion
 * si el third se pasa null es por q sera un item con subitems dentro, y entonces se pasa null y se pasan mas Destination como subitems
 * */
fun listOptionMenu(): List<List<Triple<Int, String, String?>>> {

    return listOf(
        listOf(
            Triple(
                first = Destination.HOME.iconRes,
                second = Destination.HOME.title,
                third = Destination.HOME.screenRoute
            )
        ),
        listOf(
            Triple(
                first = Destination.ABOUT_US.iconRes,
                second = Destination.ABOUT_US.title,
                third = Destination.ABOUT_US.screenRoute
            )
        ),
        listOf(
            Triple(
                first = Destination.LOGOUT.iconRes,
                second = Destination.LOGOUT.title,
                third = Destination.LOGOUT.screenRoute
            )
        )
    )
}