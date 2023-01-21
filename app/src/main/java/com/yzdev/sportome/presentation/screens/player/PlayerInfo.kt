package com.yzdev.sportome.presentation.screens.player

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.presentation.screens.detail_match.DetailMatchState
import com.yzdev.sportome.presentation.screens.detail_match.H2hMatchState
import com.yzdev.sportome.presentation.screens.detail_match.PredictionMatchState
import com.yzdev.sportome.presentation.screens.detail_match.composable.EventLayout
import com.yzdev.sportome.presentation.screens.detail_match.composable.H2hLayout
import com.yzdev.sportome.presentation.screens.detail_match.composable.LineupLayout
import com.yzdev.sportome.presentation.screens.detail_match.composable.StatsLayout
import com.yzdev.sportome.presentation.screens.player.composables.HeaderPlayer
import com.yzdev.sportome.presentation.screens.player.composables.SelectorInfoPlayer
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun PlayerInfoScreen() {
    PlayerInfoLayout()
}

@Composable
private fun PlayerInfoLayout() {

    var numberSelector by remember {
        mutableStateOf(1)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = grayBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderPlayer()

            SelectorInfoPlayer(
                numberSelector = numberSelector,
                onClick = {
                    numberSelector = it
                }
            )

            Spacer(modifier = Modifier.padding(bottom = 4.dp))

            AnimationSelector(
                numberSelector = numberSelector
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationSelector(
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
                Text(text = "resume")
            }
            2-> {
                Text(text = "Stats")
            }
            3-> {
                Text(text = "carrer")
            }
            4-> {
                Text(text = "trofeos")
            }
        }
    }
}