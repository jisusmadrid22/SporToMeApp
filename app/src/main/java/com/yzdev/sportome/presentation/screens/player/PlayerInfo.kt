package com.yzdev.sportome.presentation.screens.player

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.presentation.screens.player.composables.*
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun PlayerInfoScreen(
    viewModel: PlayerInfoViewModel,
    playerId: Int = 276
) {

    LaunchedEffect(key1 = true, block = {
        viewModel.initRequest(playerId)
    })

    PlayerInfoLayout(viewModel)
}

@Composable
private fun PlayerInfoLayout(viewModel: PlayerInfoViewModel) {

    var numberSelector by remember {
        mutableStateOf(1)
    }

    val playerInfo = viewModel.stateResumePlayer.value
    val careerPlayer = viewModel.stateCareerPlayer.value
    val trophiesPlayer = viewModel.stateTrophiesPlayer.value
    val seasonsPlayer = viewModel.stateSeasonPlayer.value

    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = grayBackground) {
        LaunchedEffect(key1 = true, block = {
            Log.e("padd", it.toString())
        })

        Column(modifier = Modifier.fillMaxSize()) {
            HeaderPlayer(
                seasonsPlayer = seasonsPlayer
            )

            SelectorInfoPlayer(
                numberSelector = numberSelector,
                onClick = {
                    numberSelector = it
                }
            )

            Spacer(modifier = Modifier.padding(bottom = 4.dp))

            AnimationSelector(
                numberSelector = numberSelector,
                playerInfo = playerInfo,
                careerPlayer = careerPlayer,
                trophiesPlayer = trophiesPlayer
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationSelector(
    numberSelector: Int,
    playerInfo: PlayerResumeState,
    careerPlayer: CareerPlayerState,
    trophiesPlayer: TrophiesPlayerState
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
                ResumePlayer(
                    playerInfo = playerInfo,
                    navigateToInfoTeam = {

                    },
                    careerPlayer = careerPlayer
                )
            }
            2-> {
                StatsPlayer(
                    playerInfo = playerInfo
                )
            }
            3-> {
                CareerPlayer(
                    careerPlayer = careerPlayer
                )
            }
            4-> {
                TrophiesPlayer(
                    trophiesPlayer = trophiesPlayer
                )
            }
        }
    }
}