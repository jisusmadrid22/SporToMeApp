@file:OptIn(ExperimentalAnimationApi::class)

package com.yzdev.sportome.presentation.screens.on_boarding

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yzdev.sportome.common.composable.screenConfig.WindowInfo
import com.yzdev.sportome.common.composable.screenConfig.rememberWindowInfo
import com.yzdev.sportome.presentation.Destination
import com.yzdev.sportome.presentation.screens.on_boarding.composables.*
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun OnBoardingScreen(
    navHostController: NavHostController,
    isNotBoarding: Boolean
) {

    if (isNotBoarding){
        navHostController.navigate(route = Destination.HOME.screenRoute){
            popUpTo(Destination.TUTORIAL.screenRoute){inclusive = true}
        }
    }else{
        OnBoardingLayout(
            navHostController = navHostController
        )
    }
}

@Composable
private fun OnBoardingLayout(
    navHostController: NavHostController
) {
    var number by remember {
        mutableStateOf(1)
    }

    val windowInfo = rememberWindowInfo()

    LaunchedEffect(key1 = true, block = {
        Log.e("WINDOWS","WINDOWS ${windowInfo.screenHeightInfo}")
    })

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = grayBackground
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            
            Box(
                modifier = Modifier.fillMaxSize()
            ){
                AnimationStep(numberStep = number)
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomEnd
            ){
                DiamondButton(
                    sizeButton = if (windowInfo.screenHeightInfo is WindowInfo.WindowType.Compact) 56.dp else 82.dp,//82.dp,
                    numberStep = number,
                    onClick = {
                        if (it < 4){
                            number++
                        }else{
                            navHostController.navigate(route = Destination.TUTORIAL.screenRoute){
                                popUpTo(Destination.ON_BOARDING.screenRoute){inclusive = true}
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AnimationStep(
    numberStep: Int
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
        when (targetCount){
            1-> {
                StepOne()
            }
            2-> {
                StepTwo()
            }
            3-> {
                StepThree()
            }
            4-> {
                StepFinish()
            }
        }
    }
}