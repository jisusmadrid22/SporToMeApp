@file:OptIn(ExperimentalAnimationApi::class)

package com.yzdev.sportome.presentation.screens.detail_match

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.composable.topBarDesign.TopBarCustomApp
import com.yzdev.sportome.domain.model.MatchesResponseLocal
import com.yzdev.sportome.presentation.screens.detail_match.composable.*
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun DetailMatch(
    idMatch: Long?,
    viewModel: DetailMatchViewModel
) {
    Scaffold(
        backgroundColor = grayBackground
    ) {
        DetailMatchLayout(
            idMatch, viewModel
        )
    }
}

@Composable
private fun DetailMatchLayout(
    idMatch: Long?,
    viewModel: DetailMatchViewModel
){

    var numberSelector by remember {
        mutableStateOf(1)
    }

    val stateDetail = viewModel.stateListDetail.value
    val h2hState = viewModel.stateH2h.value
    val statePrediction = viewModel.statePrediction.value

    /** init get details remote*/
    LaunchedEffect(key1 = true, block = {
        if (idMatch != null){
            viewModel.getDetailMatchByID(idMatch)
        }else{
            viewModel.setErrorId()
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.275f)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
                .background(MaterialTheme.colors.primary)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                TopBarCustomApp(
                    showStartButton = true,
                    iconStartButton = Icons.Rounded.ArrowBack,
                    actionStartButton = {

                    }
                ) {
                    Text(
                        text = stateDetail.info?.league?.name ?: "",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            fontFamily = QuickSandFont,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                MatchInfoCard(
                    item = stateDetail,
                )
            }
        }

        Spacer(modifier = Modifier.padding(bottom = 4.dp))

        SelectorInfo(
            numberSelector = numberSelector,
            onClick = {
                numberSelector = it
            }
        )

        if (numberSelector != 3){

            Spacer(modifier = Modifier.padding(bottom = 4.dp))

            InfoTeams(
                homeTeam = stateDetail.info?.teams?.home?.name ?: "",
                awayTeam = stateDetail.info?.teams?.away?.name ?: "",
                formationHome = if (numberSelector != 2) null else {
                    stateDetail.info?.lineups?.first()?.formation ?: ""
                },
                formationAway = if (numberSelector != 2) null else {
                    stateDetail.info?.lineups?.last()?.formation ?: ""
                }
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 4.dp))

        AnimationSelector(
            numberSelector = numberSelector,
            stateDetail = stateDetail,
            stateH2h = h2hState,
            statePrediction = statePrediction
        )

    }
}

@Composable
private fun AnimationSelector(
    numberSelector: Int,
    stateDetail: DetailMatchState,
    stateH2h: H2hMatchState,
    statePrediction: PredictionMatchState
) {
    AnimatedContent(
        targetState = numberSelector,
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
        when (targetCount){
            1-> {
                StatsLayout(
                    stateDetail
                )
            }
            2-> {
                LineupLayout(
                    stateDetail
                )
            }
            3-> {
                H2hLayout(
                    stateH2h,
                    statePrediction
                )
            }
            4-> {
                EventLayout(
                    stateDetail
                )
            }
        }
    }
}