package com.yzdev.sportome.presentation.screens.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yzdev.sportome.common.getListItemBottomApp
import com.yzdev.sportome.presentation.screens.home.composables.BottomAppDesign
import com.yzdev.sportome.presentation.screens.home.composables.NavigationHome
import com.yzdev.sportome.presentation.screens.menu.MenuScreen
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navHostControllerParent: NavHostController
) {
    val navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppDesign(
                navHostController = navHostController,
                listItems = getListItemBottomApp(),
                bottomAppBarState = true
            )
        },
        drawerContent = {
            MenuScreen(
                navHostController = navHostControllerParent,
                scaffoldState = scaffoldState
            )
        },
        drawerShape = RoundedCornerShape(topEnd = 48.dp),
        drawerBackgroundColor = grayBackground,
        backgroundColor = grayBackground
    ) {
        NavigationHome(
            navHostController = navHostController,
            scaffoldState = scaffoldState,
            homeViewModel = viewModel,
            navHostControllerParent = navHostControllerParent
        )
    }
}