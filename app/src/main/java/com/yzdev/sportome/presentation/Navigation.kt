package com.yzdev.sportome.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yzdev.sportome.presentation.screens.home.HomeScreen
import com.yzdev.sportome.presentation.screens.home.HomeViewModel
import com.yzdev.sportome.presentation.screens.tutorial.IntroTutorialScreen
import com.yzdev.sportome.presentation.screens.tutorial.TutorialViewModel

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
            val viewModel: TutorialViewModel = hiltViewModel<TutorialViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                IntroTutorialScreen(navHostController = navigation, viewModel = viewModel)
            }
        }

        /** HOME SCREEN*/
        composable(
            route = Destination.HOME.screenRoute
        ){
            val viewModel: HomeViewModel = viewModel()

            Box(modifier = Modifier.fillMaxSize()){
                HomeScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}