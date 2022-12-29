package com.yzdev.sportome.presentation.screens.home.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.yzdev.sportome.common.composable.getCurrentPage
import com.yzdev.sportome.presentation.Destination

@Composable
fun BottomAppDesign(
    navHostController: NavHostController,
    listItems: List<DestinationHome>,
    bottomAppBarState: Boolean
) {
    val currentPage = getCurrentPage(navHostController = navHostController)

    AnimatedVisibility(
        visible = bottomAppBarState,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
    ) {
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 0.dp
        ) {
            listItems.forEach {item->
                BottomNavigationItem(
                    selected = currentPage == item.screenRoute,
                    onClick = {
                        navHostController.navigate(item.screenRoute){
                            popUpTo(navHostController.graph.findStartDestination().id){
                                saveState = true
                            }

                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(imageVector = ImageVector.vectorResource(id = item.iconRes), contentDescription = item.title)
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = MaterialTheme.colors.primary,
                    unselectedContentColor = Color.Black.copy(alpha = 0.5f),
                )
            }
        }
    }
}