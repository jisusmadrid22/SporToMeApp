package com.yzdev.sportome.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yzdev.sportome.common.Constant
import com.yzdev.sportome.presentation.screens.detail_match.DetailMatch
import com.yzdev.sportome.presentation.screens.detail_match.DetailMatchViewModel
import com.yzdev.sportome.presentation.screens.home.HomeScreen
import com.yzdev.sportome.presentation.screens.home.HomeViewModel
import com.yzdev.sportome.presentation.screens.on_boarding.OnBoardingScreen
import com.yzdev.sportome.presentation.screens.player.PlayerInfoScreen
import com.yzdev.sportome.presentation.screens.player.PlayerInfoViewModel
import com.yzdev.sportome.presentation.screens.team.TeamInfoScreen
import com.yzdev.sportome.presentation.screens.team.TeamInfoViewModel
import com.yzdev.sportome.presentation.screens.tutorial.TutorialContentScreen
import com.yzdev.sportome.presentation.screens.tutorial.TutorialViewModel

@Composable
fun Navigation(
    isNotTutorial: Boolean
) {
    val navigation = rememberNavController()

    NavHost(
        navController = navigation,
        startDestination = Destination.DETAIL_TEAM.screenRoute
    ){
        /** ON BOARDING SCREEN*/
        composable(
            route = Destination.ON_BOARDING.screenRoute
        ){
            //val viewmodel = hiltViewModel<HomeViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                OnBoardingScreen(
                    navHostController = navigation,
                    isNotBoarding = isNotTutorial
                )
            }
        }

        /** TUTORIAL SCREEN*/
        composable(
            route = Destination.TUTORIAL.screenRoute
        ){
            val viewModel: TutorialViewModel = hiltViewModel<TutorialViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                TutorialContentScreen(navHostController = navigation, viewModel = viewModel)
                //IntroTutorialScreen(navHostController = navigation, viewModel = viewModel)
            }
        }

        /** HOME SCREEN*/
        composable(
            route = Destination.HOME.screenRoute
        ){
            val viewModel: HomeViewModel = viewModel()

            Box(modifier = Modifier.fillMaxSize()){
                HomeScreen(
                    viewModel = viewModel,
                    navHostControllerParent = navigation
                )
            }
        }

        /** ABOUT US SCREEN*/
        composable(
            route = Destination.ABOUT_US.screenRoute
        ){
            //val viewModel: TutorialViewModel = hiltViewModel<TutorialViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                Text(text = "About us")
            }
        }

        /** DETAIL MATCH SCREEN*/
        composable(
            route = Destination.DETAIL_MATCH.screenRoute + "/{${Constant.MATCH_ID_KEY}}",
            arguments = listOf(
                navArgument(name = Constant.MATCH_ID_KEY) { type = NavType.LongType }
            )
        ){
            val viewModel: DetailMatchViewModel = hiltViewModel<DetailMatchViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                DetailMatch(viewModel = viewModel, idMatch = it.arguments?.getLong(Constant.MATCH_ID_KEY), navHostController = navigation)
            }
        }

        /** DETAIL PLAYER SCREEN*/
        composable(
            route = Destination.DETAIL_PLAYER.screenRoute + "/{${Constant.PLAYER_ID_KEY}}",
            arguments = listOf(
                navArgument(name = Constant.PLAYER_ID_KEY) { type = NavType.LongType }
            )
        ){
            val viewModel: PlayerInfoViewModel = hiltViewModel<PlayerInfoViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                PlayerInfoScreen(viewModel = viewModel, playerId = it.arguments?.getLong(Constant.PLAYER_ID_KEY))
            }
        }

        /** DETAIL TEAM SCREEN*/
        composable(
            route = Destination.DETAIL_TEAM.screenRoute /*+ "/{${Constant.PLAYER_ID_KEY}}",
            arguments = listOf(
                navArgument(name = Constant.PLAYER_ID_KEY) { type = NavType.LongType }
            )*/
        ){
            val viewModel: TeamInfoViewModel = hiltViewModel<TeamInfoViewModel>()

            Box(modifier = Modifier.fillMaxSize()){
                TeamInfoScreen(teamInfoViewModel = viewModel, teamId = 33)
            }
        }
    }
}