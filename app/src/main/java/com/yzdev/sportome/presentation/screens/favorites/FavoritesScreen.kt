package com.yzdev.sportome.presentation.screens.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.composable.listDesign.animationList.ListLeague
import com.yzdev.sportome.common.composable.listDesign.animationList.ListTeam
import com.yzdev.sportome.common.composable.switchDesign.SwitchTypeWithContainer
import com.yzdev.sportome.common.composable.topBarDesign.TopBarModern
import com.yzdev.sportome.common.getAllLeaguesFavorites
import com.yzdev.sportome.common.getAllTeamsFavorites
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState
) {
    FavoriteLayout(scaffoldState = scaffoldState)
}

@Composable
private fun FavoriteLayout(
    scaffoldState: ScaffoldState
) {

    val scope = rememberCoroutineScope()

    val isSelected = rememberSaveable {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TopBarModern(
            iconStartButton = Icons.Rounded.Menu,
            actionStartButton = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
            textTitle = AppResource.getString(R.string.favorites)
        )

        SwitchTypeWithContainer(
            isSelected = isSelected.value,
            isSelectedOnChange = {
                isSelected.value = it
            }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
        ) {
            if (!isSelected.value){
                /** show list competition*/
                ListLeague(
                    listLeague = CompetitionState(),
                    clickItem = {

                    }
                )
            }else{
                /** show list teams*/
                ListTeam(
                    teamList = getAllTeamsFavorites(),
                    clickItem = {

                    }
                )
            }
        }
    }
}