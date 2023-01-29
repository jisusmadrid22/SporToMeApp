package com.yzdev.sportome.presentation.screens.on_boarding.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.common.composable.screenConfig.rememberWindowInfo
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun StepFinish(

) {

    val window = rememberWindowInfo()

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AutoResizedText(
                    text = stringResource(id = R.string.finishStepTitle),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        fontFamily = RobotoCondensed,
                        color = blackLight
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AutoResizedText(
                    text = stringResource(id = R.string.finishStepHint),
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 22.sp,
                        fontFamily = RobotoCondensed,
                        color = blackLight
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}