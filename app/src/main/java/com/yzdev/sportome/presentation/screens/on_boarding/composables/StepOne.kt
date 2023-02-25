package com.yzdev.sportome.presentation.screens.on_boarding.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.composable.screenConfig.rememberWindowInfo
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun StepOne(

) {

    val window = rememberWindowInfo()

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {


            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, start = 24.dp, top = 24.dp, bottom = 0.dp)
            ) {
                AutoResizedText(
                    text = stringResource(id = R.string.stepOneBoarding),
                    style = TextStyle(
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp,
                        fontFamily = RobotoCondensed,
                        color = blackLight
                    )
                )
            }

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(end = 24.dp, start = 24.dp, top = 0.dp, bottom = 0.dp)
            ) {
                AutoResizedText(
                    text = stringResource(id = R.string.stepOneBoardingTwo),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        fontFamily = RobotoCondensed,
                        color = blackLight
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(window.screenHeight * 0.5f)
                .background(gray)
            ) {

            }
        }
    }
}