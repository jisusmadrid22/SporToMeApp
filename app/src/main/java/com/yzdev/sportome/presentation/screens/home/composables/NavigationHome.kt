package com.yzdev.sportome.presentation.screens.home.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yzdev.sportome.presentation.screens.favorites.FavoriteScreen
import com.yzdev.sportome.presentation.screens.favorites.FavoriteViewModel
import com.yzdev.sportome.presentation.screens.home.HomeViewModel
import com.yzdev.sportome.presentation.screens.searcher.SearchViewModel
import com.yzdev.sportome.presentation.screens.searcher.SearcherScreen
import com.yzdev.sportome.presentation.screens.today_match.TodayMatchScreen
import com.yzdev.sportome.presentation.screens.today_match.TodayMatchViewModel

@Composable
fun NavigationHome(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState,
    homeViewModel: HomeViewModel,
    navHostControllerParent: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = DestinationHome.TODAY_MATCH.screenRoute
    ){
        /** TODAY MATCH INTO HOME SCREEN*/
        composable(
            route = DestinationHome.TODAY_MATCH.screenRoute
        ){
            val viewModel = hiltViewModel<TodayMatchViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                TodayMatchScreen(
                    scaffoldState = scaffoldState,
                    viewModel = viewModel,
                    navHostController = navHostControllerParent
                )
            }
        }

        /** FAVORITE VIEW INTO HOME SCREEN*/
        composable(
            route = DestinationHome.FAVORITES.screenRoute
        ){
            val viewModel = hiltViewModel<FavoriteViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                FavoriteScreen(navHostController = navHostController, scaffoldState = scaffoldState, viewModel = viewModel)
            }
        }

        /** SEARCH VIEW INTO HOME SCREEN*/
        composable(
            route = DestinationHome.SEARCH.screenRoute
        ){
            val viewModel = hiltViewModel<SearchViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                SearcherScreen(navHostController = navHostController, scaffoldState = scaffoldState, viewModel = viewModel)
            }
        }
    }
}