@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterialApi::class
)

package com.yzdev.sportome.presentation.screens.tutorial

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yzdev.sportome.R
import com.yzdev.sportome.common.*
import com.yzdev.sportome.common.composable.listDesign.animationList.ListCountry
import com.yzdev.sportome.common.composable.listDesign.animationList.ListLeague
import com.yzdev.sportome.common.composable.listDesign.animationList.ListSports
import com.yzdev.sportome.common.composable.listDesign.animationList.ListTeam
import com.yzdev.sportome.common.composable.topBarDesign.TopBarCustomApp
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.presentation.Destination
import com.yzdev.sportome.presentation.screens.tutorial.composables.BottomSheetTutorialDesign
import com.yzdev.sportome.presentation.screens.tutorial.composables.TextFieldTutorial
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TutorialContentScreen(
    navHostController: NavHostController,
    viewModel: TutorialViewModel
) {
    TutorialContentLayout(
        navHostController, viewModel
    )
}

@Composable
private fun TutorialContentLayout(
    navHostController: NavHostController,
    viewModel: TutorialViewModel
) {

    var textTopBar by remember {
        mutableStateOf(AppResource.getString(R.string.sportTitle))
    }

    var numberStep by remember {
        mutableStateOf(1)
    }

    val sportSelected = viewModel.sportSelected.value
    val countrySelected = viewModel.countrySelected.value
    val leagueSelected = viewModel.leagueSelected.value

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val scope = rememberCoroutineScope()

    var refreshing by remember { mutableStateOf(false) }

    val state = rememberPullRefreshState(
        refreshing,
        onRefresh = {
            scope.launch {

                when (numberStep) {
                    1 -> {

                    }
                    2 -> {
                        refreshing = true
                        viewModel.getAllCountries()
                        delay(1500)
                        refreshing = false
                    }
                    3 -> {
                        refreshing = true
                        viewModel.getAllCompetitionsRemote()
                        delay(1500)
                        refreshing = false
                    }
                    4 -> {
                        refreshing = true
                        viewModel.getAllTeamsRemote()
                        delay(1500)
                        refreshing = false
                    }
                }
            }
        }
    )

    LaunchedEffect(
        key1 = true,
        block = {
            viewModel.querySport(listParent = getAllSports())  //set all sport from api
        }
    )

    LaunchedEffect(key1 = numberStep, block = {
        when(numberStep){
            1-> textTopBar = AppResource.getString(R.string.sportTitle)
            2-> textTopBar = sportSelected?.name ?: AppResource.getString(R.string.sportTitle)
            3-> textTopBar = "${sportSelected?.name ?: AppResource.getString(R.string.sportTitle)} - ${countrySelected?.name ?: ""}"
            4-> textTopBar = "${sportSelected?.name ?: AppResource.getString(R.string.sportTitle)} - ${countrySelected?.name ?: ""} - ${leagueSelected?.name ?: ""}"
        }
    })

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primary,
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 24.dp, topStart = 24.dp),
        sheetElevation = 4.dp,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            BottomSheetTutorialDesign(numberStep = numberStep) {
                /*scope.launch {
                    scaffoldState.bottomSheetState.collapse()
                    if(numberStep < 4){
                        numberStep++
                    }else{
                        Toast.makeText(context, "to home", Toast.LENGTH_SHORT).show()
                    }
                }*/
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopBarCustomApp(
                    showStartButton = numberStep > 1,
                    iconStartButton = Icons.Rounded.ArrowBack,
                    actionStartButton = {
                        numberStep--
                    }
                ) {
                    Text(
                        text = textTopBar,
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            fontFamily = RobotoCondensed
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(top = 8.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = titleTutorialByStep(numberStep),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = RobotoCondensed
                        )
                    )

                    Spacer(modifier = Modifier.padding(top = 16.dp))

                    TextFieldTutorial(
                        value = viewModel.getQueryByNumberStep(numberStep),
                        numberStep = numberStep,
                        textOnChange = {
                            viewModel.changeQueryByNumber(
                                number = numberStep,
                                query = it
                            )

                            when (numberStep) {
                                1 -> viewModel.querySport(listParent = getAllSports())
                                /*2-> listAllCountryBySport?.let { it1 -> viewModel.filterListCountry(listParent = it1, query = viewModel.queryCountry.value) }
                                3-> listAllLeagueByCountry?.let { it1 -> viewModel.filterListLeague(listParent = it1, query = viewModel.queryLeague.value) }
                                4-> leagueSelected?.let { it1 -> viewModel.filterListTeam(listParent = it1.teams, query = viewModel.queryTeam.value) }*/
                            }
                        }
                    )

                    if (numberStep == 3) {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = RobotoCondensed,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                ) {
                                    append("Nota:")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontFamily = RobotoCondensed,
                                        fontSize = 12.sp,
                                        color = Color.White
                                    )
                                ) {
                                    append(" competiciones actuales de esta temporada o la ultima jugada")
                                }
                            }
                        )
                    } else {
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                    }

                    Spacer(modifier = Modifier.padding(top = 4.dp))

                    /** Animation list*/

                    /** Animation list*/
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .pullRefresh(state = state, enabled = numberStep != 1)
                    ) {
                        AnimationList(
                            numberStep = numberStep,
                            viewModel = viewModel,
                            countrySelected = countrySelected,
                            sportSelected = sportSelected,
                            leagueSelected = leagueSelected,
                        ) {
                            Log.e("bottomsheet", "click")
                            when (numberStep) {
                                1 -> {
                                    scope.launch {
                                        viewModel.getAllCountries()
                                        numberStep++
                                    }
                                }
                                2 -> {
                                    scope.launch {
                                        viewModel.getAllCompetitionsRemote()
                                        numberStep++
                                    }
                                }
                                3 -> {
                                    scope.launch {
                                        viewModel.getAllTeamsRemote()
                                        numberStep++
                                    }
                                }
                                4 -> {
                                    scope.launch {
                                        viewModel.saveDataTutorial()
                                        navHostController.navigate(route = Destination.HOME.screenRoute) {
                                            popUpTo(Destination.TUTORIAL.screenRoute) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                }
                            }
                            /*scope.launch {
                                if(scaffoldState.bottomSheetState.isCollapsed){
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }*/
                        }
                    }
                }
            }

            PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))

        }
    }
}

@Composable
private fun AnimationList(
    viewModel: TutorialViewModel,
    sportSelected: Sport?,
    countrySelected: LocalCountry?,
    leagueSelected: LocalCompetition?,
    numberStep: Int,
    numberStepOnChange: ()-> Unit
) {

    val listCountries = viewModel.stateListCountry.value
    val listCompetition = viewModel.stateListCompetition.value
    val listTeam = viewModel.stateListTeam.value

    //filters
    val listSportFiltered = viewModel.filteredSport
    val listCountriesFiltered = viewModel.filteredCountries
    val listCompetitionFiltered = viewModel.filteredCompetition
    val listTeamFiltered = viewModel.filteredTeam

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
                    ListSports(
                        listSport = listSportFiltered
                    ) {
                        viewModel.changeSport(it)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                2-> sportSelected?.let {
                    ListCountry(
                        listCountry = listCountries,
                        filteredList = listCountriesFiltered,
                        onSuccess = {
                            viewModel.queryCountries(listCountries.info ?: emptyList())
                        }
                    ){country->
                        viewModel.changeCountry(country)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                3-> countrySelected?.let {
                    ListLeague(
                        listLeague = listCompetition,
                        filteredList = listCompetitionFiltered,
                        onSuccess = {
                            viewModel.queryCompetition(listCompetition.info ?: emptyList())
                        }
                    ){league->
                        viewModel.changeLeague(league)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                4-> leagueSelected?.let {
                    ListTeam(
                        teamList = listTeam,
                        filteredList = listTeamFiltered,
                        onSuccess = {
                            viewModel.queryTeam(listTeam.info ?: emptyList())
                        }
                    ){team->
                        viewModel.changeTeam(team)
                        numberStepOnChange()
                    }
                }
            }
        }
    }

}

/*@Preview(showSystemUi = true)
@Composable
fun PreviewLayout() {
    Box(modifier = Modifier.fillMaxSize()){
        TutorialContentLayout()
    }
}*/
