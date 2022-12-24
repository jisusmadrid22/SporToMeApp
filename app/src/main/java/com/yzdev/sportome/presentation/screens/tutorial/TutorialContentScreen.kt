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
import com.yzdev.sportome.common.Country
import com.yzdev.sportome.common.League
import com.yzdev.sportome.common.Sport
import com.yzdev.sportome.common.composable.listDesign.animationList.ListCountry
import com.yzdev.sportome.common.composable.listDesign.animationList.ListLeague
import com.yzdev.sportome.common.composable.listDesign.animationList.ListSports
import com.yzdev.sportome.common.composable.listDesign.animationList.ListTeam
import com.yzdev.sportome.common.composable.topBarDesign.TopBarCustomApp
import com.yzdev.sportome.common.titleTutorialByStep
import com.yzdev.sportome.presentation.screens.tutorial.composables.TextFieldTutorial
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun TutorialContentScreen(
    navHostController: NavHostController
) {
    TutorialContentLayout()
}

@Composable
private fun TutorialContentLayout(

) {

    var textTopBar by remember {
        mutableStateOf("Deporte")
    }

    var numberStep by remember {
        mutableStateOf(1)
    }

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
                    numberStep = numberStep,
                    textOnChange = {}
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
                AnimationList(numberStep = numberStep){
                    numberStep++
                }
            }
        }
    }
}

@Composable
private fun AnimationList(
    numberStep: Int,
    numberStepOnChange: ()-> Unit
) {

    /** change to main viemodel*/
    var sportSelected: Sport? by rememberSaveable {
        mutableStateOf(null)
    }

    var countrySelected: Country? by rememberSaveable {
        mutableStateOf(null)
    }

    var leagueSelected: League? by rememberSaveable {
        mutableStateOf(null)
    }
    /**************************/

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
                1-> ListSports(){
                    sportSelected = it
                    numberStepOnChange()
                }
                2-> sportSelected?.let {
                    ListCountry(
                        sportId = it.id
                    ){country->
                        countrySelected = country
                        numberStepOnChange()
                    }
                }
                3-> countrySelected?.let {
                    ListLeague(
                        countryId = it.id
                    ){league->
                        leagueSelected = league
                        numberStepOnChange()
                    }
                }
                4-> leagueSelected?.let {
                    ListTeam(
                        teamList = it.teams
                    ){
                        //todo set action
                    }
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewLayout() {
    Box(modifier = Modifier.fillMaxSize()){
        TutorialContentLayout()
    }
}
