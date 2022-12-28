package com.yzdev.sportome.common.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun getCurrentPage(
    navHostController: NavHostController
): String? {
    val navBackEntry by navHostController.currentBackStackEntryAsState()
    return navBackEntry?.destination?.route
}