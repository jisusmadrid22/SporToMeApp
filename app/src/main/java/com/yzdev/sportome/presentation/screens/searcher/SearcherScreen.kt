@file:OptIn(ExperimentalAnimationApi::class)

package com.yzdev.sportome.presentation.screens.searcher

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yzdev.sportome.R
import com.yzdev.sportome.common.*
import com.yzdev.sportome.common.composable.listDesign.animationList.ListCountry
import com.yzdev.sportome.common.composable.listDesign.animationList.ListLeague
import com.yzdev.sportome.common.composable.listDesign.animationList.ListSports
import com.yzdev.sportome.common.composable.listDesign.animationList.ListTeam
import com.yzdev.sportome.common.composable.switchDesign.SwitchTypeWithContainer
import com.yzdev.sportome.common.composable.topBarDesign.BarSecondary
import com.yzdev.sportome.common.composable.topBarDesign.TopBarModern
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.presentation.Destination
import com.yzdev.sportome.presentation.screens.searcher.composable.SearcherField
import com.yzdev.sportome.presentation.screens.tutorial.CompetitionState
import com.yzdev.sportome.presentation.screens.tutorial.TeamState
import com.yzdev.sportome.presentation.screens.tutorial.TutorialViewModel
import com.yzdev.sportome.presentation.screens.tutorial.composables.TextFieldTutorial
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.grayBackground
import kotlinx.coroutines.launch

@Composable
fun SearcherScreen(
    navHostController: NavHostController,
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel
) {
    SearcherLayout(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    )
}

@Composable
private fun SearcherLayout(
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel
) {

    val scope = rememberCoroutineScope()

    var numberStep by remember {
        mutableStateOf(1)
    }
    val countrySelected = viewModel.countrySelected.value
    val leagueSelected = viewModel.leagueSelected.value

    var textTopBar by remember {
        mutableStateOf(AppResource.getString(R.string.sportTitle))
    }

    LaunchedEffect(key1 = numberStep, block = {
        when(numberStep){
            1-> textTopBar = AppResource.getString(R.string.selectCountry)
            2-> textTopBar = countrySelected?.name ?: AppResource.getString(R.string.countryFavorite)
        }
    })

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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                TextFieldTutorial(
                    value = viewModel.getQueryByNumberStep(numberStep),
                    numberStep = numberStep,
                    background = grayBackground,
                    placeholder = labelTextFieldByStepSearch(numberStep),
                    textOnChange = {
                        viewModel.changeQueryByNumber(
                            number = numberStep,
                            query = it
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 6.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)) {
            BarSecondary(
                showStartButton = numberStep > 1,
                iconStartButton = Icons.Rounded.ArrowBack,
                actionStartButton = {
                    numberStep--
                }
            ) {
                Text(
                    text = textTopBar,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = QuickSandFont
                    )
                )
            }
        }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
        ) {
            AnimationList(
                viewModel = viewModel,
                countrySelected = countrySelected,
                leagueSelected = leagueSelected,
                numberStep = numberStep
            ) {
                when(numberStep){
                    1->{
                        scope.launch {
                            viewModel.getAllCompetitionsRemote()
                            numberStep++
                        }
                    }
                    2->{
                        //navigate to info competition
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimationList(
    viewModel: SearchViewModel,
    countrySelected: LocalCountry?,
    leagueSelected: LocalCompetition?,
    numberStep: Int,
    numberStepOnChange: ()-> Unit
) {

    val listCountries = viewModel.stateListCountry.value
    val listCompetition = viewModel.stateListCompetition.value

    //filters
    val listCountriesFiltered = viewModel.filteredCountries
    val listCompetitionFiltered = viewModel.filteredCompetition

    AnimatedContent(
        targetState = numberStep,
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
                1-> {
                    ListCountry(
                        listCountry = listCountries,
                        filteredList = listCountriesFiltered,
                        showPadding = true,
                        onSuccess = {
                            viewModel.queryCountries(listCountries.info ?: emptyList())
                        }
                    ){country->
                        viewModel.changeCountry(country)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                2-> countrySelected?.let {
                    ListLeague(
                        listLeague = listCompetition,
                        filteredList = listCompetitionFiltered,
                        showPadding = true,
                        onSuccess = {
                            viewModel.queryCompetition(listCompetition.info ?: emptyList())
                        }
                    ){league->
                        viewModel.changeLeague(league)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
            }
        }
    }

}