package com.yzdev.sportome.presentation.screens.searcher

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.Team
import com.yzdev.sportome.common.composable.listDesign.animationList.ListLeague
import com.yzdev.sportome.common.composable.listDesign.animationList.ListTeam
import com.yzdev.sportome.common.composable.switchDesign.SwitchTypeWithContainer
import com.yzdev.sportome.common.composable.topBarDesign.TopBarModern
import com.yzdev.sportome.common.getAllLeaguesFavorites
import com.yzdev.sportome.common.getAllTeamsFavorites
import com.yzdev.sportome.presentation.screens.searcher.composable.SearcherField
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.screens.tutorial.TeamState
import kotlinx.coroutines.launch

@Composable
fun SearcherScreen(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState
) {
    SearcherLayout(
        scaffoldState = scaffoldState
    )
}

@Composable
private fun SearcherLayout(
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
            textTitle = AppResource.getString(R.string.searcher)
        )

        SwitchTypeWithContainer(
            isSelected = isSelected.value,
            isSelectedOnChange = {
                isSelected.value = it
            }
        )

        Spacer(modifier = Modifier.padding(top = 14.dp))

        SearcherField(
            selectedSwitch = isSelected.value,
            searcherClick = {

            }
        )

        Spacer(modifier = Modifier.padding(top = 6.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
        ) {
            if (!isSelected.value){
                /** show list competition*/
                ListLeague(
                    listLeague = CompetitionState(),
                    clickItem = {

                    },
                    onSuccess = {

                    },
                    filteredList = emptyList()
                )
            }else{
                /** show list teams*/
                ListTeam(
                    teamList = TeamState(),
                    clickItem = {

                    },
                    onSuccess = {

                    },
                    filteredList = emptyList()
                )
            }
        }
    }
}