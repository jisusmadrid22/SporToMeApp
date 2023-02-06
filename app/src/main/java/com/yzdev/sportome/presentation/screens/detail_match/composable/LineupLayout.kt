package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.getFirstAndLastName
import com.yzdev.sportome.presentation.screens.detail_match.DetailMatchState
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight

@Composable
fun LineupLayout(
    stateDetail: DetailMatchState
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        when{
            stateDetail.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            stateDetail.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stateDetail.error,
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                    fontFamily = RobotoCondensed,
                                    color = blackLight
                                ),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
            else -> {
                if (stateDetail.info?.statistics != null){
                    if (stateDetail.info.statistics.isNotEmpty()){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            LineupLayoutDesign(stateDetail = stateDetail)
                        }
                    }else{
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 24.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.lineupEmpty),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 20.sp,
                                            fontFamily = RobotoCondensed,
                                            color = blackLight
                                        ),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }else{
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.erroGeneric),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        fontFamily = RobotoCondensed,
                                        color = blackLight
                                    ),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LineupLayoutDesign(
    stateDetail: DetailMatchState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(6.dp))

        LineupField()

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = stringResource(R.string.managerTitle),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = RobotoCondensed,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stateDetail.info?.lineups?.first()?.coach?.name ?: "",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.Start
            )

            Text(
                text = stateDetail.info?.lineups?.last()?.coach?.name ?: "",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.lineupTitle),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = RobotoCondensed,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.5f), horizontalAlignment = Alignment.Start) {
                (stateDetail.info?.lineups?.first()?.startXI ?: emptyList()).forEach {

                    val names = getFirstAndLastName(it.player.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = RobotoCondensed,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.player.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                (stateDetail.info?.lineups?.last()?.startXI ?: emptyList()).forEach {

                    val names = getFirstAndLastName(it.player.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = RobotoCondensed,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.player.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.End
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.substituteTitle),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = RobotoCondensed,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.fillMaxWidth(0.5f), horizontalAlignment = Alignment.Start) {
                (stateDetail.info?.lineups?.first()?.substitutes ?: emptyList()).forEach {

                    val names = getFirstAndLastName(it.player.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = RobotoCondensed,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.player.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.Start
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End) {
                (stateDetail.info?.lineups?.last()?.substitutes ?: emptyList()).forEach {

                    val names = getFirstAndLastName(it.player.name)

                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = RobotoCondensed,
                                    color = Color.Black.copy(alpha = 0.25f),
                                )
                            ){
                                append("${it.player.number}. ${names.first}")
                            }
                            if (names.second.isNotEmpty()){
                                append("\n")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black,
                                    )
                                ){
                                    append(names.second)
                                }
                            }
                        },
                        textAlign = TextAlign.End
                    )

                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

    }
}