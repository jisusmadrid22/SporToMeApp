package com.yzdev.sportome.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yzdev.sportome.common.getAllSports
import com.yzdev.sportome.common.getCountryBySport
import com.yzdev.sportome.common.getLeaguesByCountry
import com.yzdev.sportome.presentation.screens.tutorial.TutorialScreen

@Composable
fun Navigation() {
    val navigation = rememberNavController()

    NavHost(
        navController = navigation,
        startDestination = Destination.TUTORIAL.screenRoute
    ){
        /** ON BOARDING SCREEN*/
        composable(
            route = Destination.ON_BOARDING.screenRoute
        ){
            //val viewmodel = hiltViewModel<HomeViewModel>()

            Box(modifier = Modifier.fillMaxSize()){

            }
        }

        /** TUTORIAL SCREEN*/
        composable(
            route = Destination.TUTORIAL.screenRoute
        ){
            //val viewmodel = hiltViewModel<HomeViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                TutorialScreen(navHostController = navigation)
            }
        }

        /** HOME SCREEN*/
        composable(
            route = Destination.HOME.screenRoute
        ){
            //val viewmodel = hiltViewModel<HomeViewModel>()

            Box(modifier = Modifier.fillMaxSize()){

            }
        }
    }
}