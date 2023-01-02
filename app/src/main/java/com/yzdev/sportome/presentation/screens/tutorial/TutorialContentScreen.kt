@file:OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.tutorial

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
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

    val listAllSport = produceState(initialValue = emptyList<Sport>(), producer = {
        value = getAllSports()
    })
    var listAllCountryBySport: List<Country>? by remember {
        mutableStateOf(null)
    }
    var listAllLeagueByCountry: List<League>? by remember {
        mutableStateOf(null)
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
    )

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = sportSelected, block = {
        if(sportSelected != null){
            listAllCountryBySport = getCountryBySport(sportSelected.id)
        }
    })

    LaunchedEffect(key1 = countrySelected, block = {
        if(countrySelected != null){
            listAllLeagueByCountry = getLeaguesByCountry(countrySelected.id)
        }
    })

    LaunchedEffect(key1 = numberStep, block = {
        when(numberStep){
            1-> {
                textTopBar = AppResource.getString(R.string.sportTitle)

            }
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
                        fontFamily = QuickSandFont
                    )
                )
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = titleTutorialByStep(numberStep),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        fontFamily = QuickSandFont
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
                    }
                )

                if(numberStep == 3){
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = QuickSandFont,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            ){
                                append("Nota:")
                            }

                            withStyle(
                                style = SpanStyle(
                                    fontFamily = QuickSandFont,
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            ){
                                append(" competiciones actuales de esta temporada o la ultima jugada")
                            }
                        }
                    )
                }else{
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                }

                Spacer(modifier = Modifier.padding(top = 4.dp))

                /** Animation list*/
                AnimationList(
                    numberStep = numberStep,
                    viewModel = viewModel,
                    countrySelected = countrySelected,
                    sportSelected = sportSelected,
                    leagueSelected = leagueSelected,
                ){
                    Log.e("bottomsheet", "click")
                    when(numberStep){
                        1->{
                            scope.launch {
                                viewModel.getAllCountries()
                                numberStep++
                            }
                        }
                        2->{
                            scope.launch {
                                viewModel.getCompetitionRemote()
                                numberStep++
                            }
                        }
                        3->{
                            numberStep++
                        }
                        4->{
                            navHostController.navigate(route = Destination.HOME.screenRoute){
                                popUpTo(Destination.TUTORIAL.screenRoute){inclusive = true}
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
                        listSport = listOf(getAllSports().first())
                    ) {
                        viewModel.changeSport(it)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                2-> sportSelected?.let {
                    ListCountry(
                        listCountry = listCountries
                    ){country->
                        viewModel.changeCountry(country)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                3-> countrySelected?.let {
                    ListLeague(
                        listLeague = listCompetition
                    ){league->
                        viewModel.changeLeague(league)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                4-> leagueSelected?.let {
                    ListTeam(
                        teamList = getAllLeagues().first().teams
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
