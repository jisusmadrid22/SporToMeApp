package com.yzdev.sportome.presentation.screens.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yzdev.sportome.presentation.screens.favorites.FavoriteScreen
import com.yzdev.sportome.presentation.screens.home.HomeViewModel
import com.yzdev.sportome.presentation.screens.searcher.SearcherScreen
import com.yzdev.sportome.presentation.screens.today_match.TodayMatchScreen

@Composable
fun NavigationHome(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState,
    homeViewModel: HomeViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = DestinationHome.TODAY_MATCH.screenRoute
    ){
        /** TODAY MATCH INTO HOME SCREEN*/
        composable(
            route = DestinationHome.TODAY_MATCH.screenRoute
        ){
            //val viewmodel = hiltViewModel<HomeViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                TodayMatchScreen(
                    scaffoldState = scaffoldState,
                    homeViewModel = homeViewModel
                )
            }
        }

        /** FAVORITE VIEW INTO HOME SCREEN*/
        composable(
            route = DestinationHome.FAVORITES.screenRoute
        ){
            //val viewmodel = hiltViewModel<HomeViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                FavoriteScreen(navHostController = navHostController, scaffoldState = scaffoldState)
            }
        }

        /** SEARCH VIEW INTO HOME SCREEN*/
        composable(
            route = DestinationHome.SEARCH.screenRoute
        ){
            //val viewmodel = hiltViewModel<HomeViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                SearcherScreen(navHostController = navHostController, scaffoldState = scaffoldState)
            }
        }
    }
}