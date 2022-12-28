package com.yzdev.sportome.presentation.screens.home

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.yzdev.sportome.common.getListItemBottomApp
import com.yzdev.sportome.presentation.screens.home.composables.BottomAppDesign
import com.yzdev.sportome.presentation.screens.home.composables.NavigationHome
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun HomeScreen() {
    val navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppDesign(
                navHostController = navHostController,
                listItems = getListItemBottomApp()
            )
        },
        drawerContent = {
            Text(text = "Mneu")
        },
        backgroundColor = grayBackground
    ) {
        NavigationHome(navHostController = navHostController, scaffoldState = scaffoldState)
    }
}