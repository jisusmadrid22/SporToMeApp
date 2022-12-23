package com.yzdev.sportome.presentation.screens.tutorial

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yzdev.sportome.presentation.screens.tutorial.composables.BottomCircleTutorialOne
import com.yzdev.sportome.presentation.screens.tutorial.composables.ContentBottomCircle
import com.yzdev.sportome.presentation.screens.tutorial.composables.ContentCenterTutorialOne
import com.yzdev.sportome.presentation.screens.tutorial.composables.TopHeaderTutorialOne

@Composable
fun TutorialScreen(
    navHostController: NavHostController
) {
    TutorialLayout()
}

@Composable
private fun TutorialLayout(

) {
    val interactionSource = remember { MutableInteractionSource() }
    var scrollController by remember {
        mutableStateOf(false)
    }

    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopHeaderTutorialOne()

            ContentCenterTutorialOne("Para comenzar, dinos cual es tu deporte y equipo favorito")

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp * 0.275f)
                .scrollable(
                    state = rememberScrollableState { delta ->
                        if (!scrollController){
                            scrollController = true
                            Log.e("delta", "scroll")
                        }else{
                            //nothing for now
                        }
                        delta
                    },
                    orientation = Orientation.Vertical
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    Log.e("delta", "click")
                }
            ) {  //valor fijo muy cercano al diseño -> 212dp
                BottomCircleTutorialOne()
                ContentBottomCircle(
                    textOne = "Presione o deslice",
                    textTwo = "para comenzar el proceso"
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewLayout(
    
) {
    Box(modifier = Modifier.fillMaxSize()) {
        TutorialLayout()
    }
}