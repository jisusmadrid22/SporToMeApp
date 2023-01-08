package com.yzdev.sportome.presentation.screens.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yzdev.sportome.common.composable.getCurrentPage
import com.yzdev.sportome.common.listOptionMenu
import com.yzdev.sportome.presentation.Destination
import com.yzdev.sportome.presentation.screens.menu.composable.HeaderMenu
import com.yzdev.sportome.presentation.screens.menu.composable.ItemMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState
) {
    MenuLayout(
        navHostController = navHostController,
        scaffoldState = scaffoldState
    )
}

@Composable
private fun MenuLayout(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState,
) {
    val currentPage = getCurrentPage(navHostController = navHostController)
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        HeaderMenu {

        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 24.dp)
        ){
            items(listOptionMenu()){ itemMenu->
                ItemMenu(
                    item = itemMenu,
                    selected = currentPage == itemMenu.first().third,
                    navigate = {
                        if (it == Destination.LOGOUT.screenRoute){
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        } else {
                            navHostController.navigate(it){
                                currentPage?.let { it1 ->
                                    popUpTo(it1){
                                        inclusive = true
                                        saveState = false
                                    }
                                }
                                launchSingleTop = true
                            }

                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    }
                )
            }
        }
    }
}