package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed

@Composable
fun ContentBottomCircle(
    textOne: String = AppResource.getString(R.string.clickText),
    textTwo: String = AppResource.getString(R.string.startProgressText),
    alpha: Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 24.dp, vertical = 32.dp)
            .graphicsLayer(alpha = alpha),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        /*Icon(
            imageVector = Icons.Rounded.PlayArrow,
            contentDescription = "",
            tint = Color.White
        )*/
        ArrowAnimationTutorial()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_play),
                contentDescription = "",
                tint = Color.White
            )

            Spacer(modifier = Modifier.padding(top = 12.dp))

            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontFamily = RobotoCondensed,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    ){
                        append(textOne)
                    }

                    withStyle(
                        style = SpanStyle(
                            fontFamily = RobotoCondensed,
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    ){
                        append(" $textTwo")
                    }
                }
            )
        }
    }
}

/*
@Preview(showSystemUi = true)
@Composable
fun PreviewContent() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenHeightDp.dp * 0.275f)) {  //valor fijo muy cercano al diseño -> 212dp
            BottomCircleTutorialOne()
            ContentBottomCircle(
                textOne = "Click",
                textTwo = "para comenzar el proceso"
            )
        }
    }
}*/
