package com.yzdev.sportome.common

fun titleTutorialByStep(
    numberStep: Int
): String{
    return when(numberStep){
        1-> "$numberStep. Deporte favorito"
        2-> "$numberStep. Pais de tu equipo favorito"
        3-> "$numberStep. Competicion de tu equipo favorito"
        4-> "$numberStep. Equipo favorito"
        else -> ""
    }
}

fun labelTextFieldByStep(
    numberStep: Int
): String{
    return when(numberStep){
        1-> "Buscar deporte"
        2-> "Buscar pais"
        3-> "Buscar competicion"
        4-> "Buscar equipo"
        else -> ""
    }
}