@file:OptIn(ExperimentalAnimationApi::class, ExperimentalAnimationApi::class)

package com.yzdev.sportome.presentation.screens.favorites

import android.view.animation.Animation.AnimationListener
import androidx.compose.animation.*
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
import com.yzdev.sportome.common.composable.listDesign.animationList.ListCountry
import com.yzdev.sportome.common.composable.listDesign.animationList.ListLeague
import com.yzdev.sportome.common.composable.listDesign.animationList.ListSports
import com.yzdev.sportome.common.composable.listDesign.animationList.ListTeam
import com.yzdev.sportome.common.composable.switchDesign.SwitchTypeWithContainer
import com.yzdev.sportome.common.composable.topBarDesign.TopBarModern
import com.yzdev.sportome.common.getAllLeaguesFavorites
import com.yzdev.sportome.common.getAllSports
import com.yzdev.sportome.common.getAllTeamsFavorites
import com.yzdev.sportome.presentation.screens.home.HomeViewModel
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.screens.tutorial.TeamState
import com.yzdev.sportome.presentation.screens.tutorial.composables.TextFieldTutorial
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: FavoriteViewModel
) {
    FavoriteLayout(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    )
}

@Composable
private fun FavoriteLayout(
    scaffoldState: ScaffoldState,
    viewModel: FavoriteViewModel
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            TextFieldTutorial(
                value = viewModel.getQueryByNumberStep(isSelected.value),
                numberStep = if (isSelected.value) 4 else 3,
                textOnChange = {
                    viewModel.changeQueryByNumber(
                        state = isSelected.value,
                        query = it
                    )
                }
            )
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        AnimationList(
            selectedSwitch = if (isSelected.value) 1 else 0,
            viewModel = viewModel
        )
    }
}

@Composable
fun AnimationList(
    selectedSwitch: Int,
    viewModel: FavoriteViewModel
) {
    val listCompetition = viewModel.stateListCompetition.value
    val listTeam = viewModel.stateListTeam.value

    //filters
    val listCompetitionFiltered = viewModel.filteredCompetition
    val listTeamFiltered = viewModel.filteredTeam

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 24.dp)
    ) {
        AnimatedContent(
            targetState = selectedSwitch,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInHorizontally { width -> width } + fadeIn() with
                            slideOutHorizontally { width -> -width } + fadeOut()
                } else {
                    slideInHorizontally { height -> -height } + fadeIn() with
                            slideOutHorizontally { height -> height } + fadeOut()
                }.using(
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Box(modifier = Modifier.fillMaxWidth()) {
                when(targetCount){
                    0->{
                        /** show list competition*/
                        ListLeague(
                            listLeague = listCompetition,
                            clickItem = {

                            },
                            onSuccess = {
                                viewModel.queryCompetition(listCompetition.info ?: emptyList())
                            },
                            filteredList = listCompetitionFiltered
                        )
                    }
                    1->{
                        /** show list teams*/
                        ListTeam(
                            teamList = listTeam,
                            clickItem = {

                            },
                            onSuccess = {
                                viewModel.queryTeam(listTeam.info ?: emptyList())
                            },
                            filteredList = listTeamFiltered
                        )
                    }
                }
            }
        }
    }
}