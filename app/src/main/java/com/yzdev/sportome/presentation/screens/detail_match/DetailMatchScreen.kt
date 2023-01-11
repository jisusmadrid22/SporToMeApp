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
) {
    Scaffold(
        backgroundColor = grayBackground
    ) {
        DetailMatchLayout()
    }
}

@Composable
private fun DetailMatchLayout(){

    var numberSelector by remember {
        mutableStateOf(1)
    }

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
                        text = "Nombre competicion",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            fontFamily = QuickSandFont
                        ),
                        textAlign = TextAlign.Center
                    )
                }

                MatchInfoCard(
                    item = null,
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
                homeTeam = "Inter de Madrid",
                awayTeam = "Paris saint germany",
                formationHome = if (numberSelector != 2) null else {"4-4-2"},
                formationAway = if (numberSelector != 2) null else {"4-3-2-1"}
            )
        }

        Spacer(modifier = Modifier.padding(bottom = 4.dp))

        AnimationSelector(numberSelector = numberSelector)

    }
}

@Composable
fun AnimationSelector(
    numberSelector: Int
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
                StatsLayout()
            }
            2-> {
                LineupLayout()
            }
            3-> {

            }
            4-> {

            }
        }
    }
}