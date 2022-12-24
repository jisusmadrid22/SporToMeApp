@file:OptIn(ExperimentalAnimationApi::class)

package com.yzdev.sportome.presentation.screens.tutorial

import android.view.animation.Animation.AnimationListener
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.yzdev.sportome.common.*
import com.yzdev.sportome.common.composable.listDesign.animationList.ListCountry
import com.yzdev.sportome.common.composable.listDesign.animationList.ListLeague
import com.yzdev.sportome.common.composable.listDesign.animationList.ListSports
import com.yzdev.sportome.common.composable.listDesign.animationList.ListTeam
import com.yzdev.sportome.common.composable.topBarDesign.TopBarCustomApp
import com.yzdev.sportome.presentation.screens.tutorial.composables.TextFieldTutorial
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

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
        mutableStateOf("Deporte")
    }

    var numberStep by remember {
        mutableStateOf(1)
    }

    val listSport = viewModel.filteredListSport
    val listCountry = viewModel.filteredListCountry
    val listLeague = viewModel.filteredListLeague
    val listTeam = viewModel.filteredListTeam

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

    LaunchedEffect(
        key1 = true,
        block = {
            viewModel.filterListSport(listParent = listAllSport.value, query = "")  //set all sport from api
        }
    )

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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.primary
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
                        when(numberStep){
                            1-> viewModel.filterListSport(listParent = listAllSport.value, query = viewModel.querySport.value)
                            2-> listAllCountryBySport?.let { it1 -> viewModel.filterListCountry(listParent = it1, query = viewModel.queryCountry.value) }
                            3-> listAllLeagueByCountry?.let { it1 -> viewModel.filterListLeague(listParent = it1, query = viewModel.queryLeague.value) }
                            4-> leagueSelected?.let { it1 -> viewModel.filterListTeam(listParent = it1.teams, query = viewModel.queryTeam.value) }
                        }
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
                    listLeague = listLeague,
                    listCountry = listCountry,
                    listSport = listSport,
                    listTeam = listTeam,
                ){
                    if(numberStep == 4){
                        //navigate
                    }else{
                        numberStep++
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimationList(
    viewModel: TutorialViewModel,
    sportSelected: Sport?,
    countrySelected: Country?,
    leagueSelected: League?,
    listSport: List<Sport>,
    listCountry: List<Country>,
    listLeague: List<League>,
    listTeam: List<Team>,
    numberStep: Int,
    numberStepOnChange: ()-> Unit
) {
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
                1-> ListSports(
                    listSport = listSport
                ){
                    viewModel.changeSport(it)
                    viewModel.clearQuery(numberStep)
                    numberStepOnChange()
                }
                2-> sportSelected?.let {
                    ListCountry(
                        sportId = it.id,
                        listCountry = listCountry
                    ){country->
                        viewModel.changeCountry(country)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                3-> countrySelected?.let {
                    ListLeague(
                        countryId = it.id,
                        listLeague = listLeague
                    ){league->
                        viewModel.changeLeague(league)
                        viewModel.clearQuery(numberStep)
                        numberStepOnChange()
                    }
                }
                4-> leagueSelected?.let {
                    ListTeam(
                        teamList = listTeam
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