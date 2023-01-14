package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.common.unixToDateTimeSA
import com.yzdev.sportome.domain.model.H2hResponse
import com.yzdev.sportome.presentation.screens.detail_match.H2hMatchState
import com.yzdev.sportome.presentation.screens.detail_match.PredictionMatchState
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun H2hLayout(
    h2hState: H2hMatchState,
    statePrediction: PredictionMatchState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn{
                when{
                    statePrediction.isLoading -> {
                        item {
                            CircularProgressIndicator()
                        }

                        item {
                            Spacer(modifier = Modifier.height(48.dp))
                        }
                    }
                    statePrediction.error.isNotEmpty()->{
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = "Error")
                                }
                            }
                        }
                    }
                    else->{

                        if (statePrediction.info.isNotEmpty()){
                            items(statePrediction.info){response->
                                LinearProgressStat(
                                    valueParent = 100,
                                    valueHome = getValueToInt(response?.comparison?.form?.home),
                                    nameState = stringResource(id = R.string.formTitle),
                                    isPercent = true
                                )
                                LinearProgressStat(
                                    valueParent = 100,
                                    valueHome = getValueToInt(response?.comparison?.att?.home),
                                    nameState = stringResource(id = R.string.attTitle),
                                    isPercent = true
                                )
                                LinearProgressStat(
                                    valueParent = 100,
                                    valueHome = getValueToInt(response?.comparison?.def?.home),
                                    nameState = stringResource(id = R.string.defTitle),
                                    isPercent = true
                                )
                                LinearProgressStat(
                                    valueParent = 100,
                                    valueHome = getValueToInt(response?.comparison?.poisson_distribution?.home),
                                    nameState = stringResource(id = R.string.poissonDistribution),
                                    isPercent = true
                                )
                                LinearProgressStat(
                                    valueParent = 100,
                                    valueHome = getValueToInt(response?.comparison?.h2h?.home),
                                    nameState = stringResource(id = R.string.h2hTitle),
                                    isPercent = true
                                )
                                LinearProgressStat(
                                    valueParent = 100,
                                    valueHome = getValueToInt(response?.comparison?.goals?.home),
                                    nameState = stringResource(id = R.string.goalsTitle),
                                    isPercent = true
                                )
                                LinearProgressStat(
                                    valueParent = 100,
                                    valueHome = getValueToFloat(response?.comparison?.total?.home),
                                    nameState = stringResource(id = R.string.totalTitle),
                                    isPercent = true
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.height(24.dp))
                            }
                        }else{
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Error")
                                    }
                                }
                            }
                        }
                    }
                }

                /** h2h*/
                when{
                    h2hState.isLoading -> {
                        items(List<String?>(5){null}){response->
                            H2hItemMatch(null)
                        }

                        item {
                            Spacer(modifier = Modifier.height(48.dp))
                        }
                    }
                    h2hState.error.isNotEmpty()->{
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(text = "Error")
                                }
                            }
                        }
                    }
                    else->{

                        if (h2hState.info.isNotEmpty()){
                            item {
                                Column {
                                    Text(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 24.dp),
                                        text = AppResource.getString(R.string.matchTitle),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            fontFamily = QuickSandFont,
                                            color = Color.Black
                                        ),
                                        textAlign = TextAlign.Start
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))
                                }
                            }

                            items(h2hState.info){response->
                                H2hItemMatch(response)
                            }

                            item {
                                Spacer(modifier = Modifier.height(48.dp))
                            }
                        }else{
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(text = "Error")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        /*LinearProgressStat(valueParent = 30f, valueHome = 15f)
        LinearProgressStat(valueParent = 3f, valueHome = 1f)
        LinearProgressStat(valueParent = 5f, valueHome = 2f)
        LinearProgressStat(valueParent = 16f, valueHome = 8f)
        LinearProgressStat(valueParent = 1f, valueHome = 0f)*//*

        Spacer(modifier = Modifier.height(12.dp))*/

        /*Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when{
                h2hState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        LazyColumn{
                            items(List<String?>(5){null}){response->
                                H2hItemMatch(null)
                            }

                            item {
                                Spacer(modifier = Modifier.height(48.dp))
                            }
                        }
                    }
                }
                h2hState.error.isNotEmpty()->{
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
                            Text(text = "Error")
                        }
                    }
                }
                else->{

                    if (h2hState.info.isNotEmpty()){
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            LazyColumn{
                                item {
                                    Column {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 24.dp),
                                            text = AppResource.getString(R.string.matchTitle),
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 12.sp,
                                                fontFamily = QuickSandFont,
                                                color = Color.Black
                                            ),
                                            textAlign = TextAlign.Start
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))
                                    }
                                }

                                items(h2hState.info){response->
                                    H2hItemMatch(response)
                                }

                                item {
                                    Spacer(modifier = Modifier.height(48.dp))
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
                                Text(text = "Error")
                            }
                        }
                    }
                }
            }
        }*/
    }
}

@Composable
private fun H2hItemMatch(
    response: H2hResponse?
) {
    if (response != null){
        H2hCardItem(response)
    }else{
        H2hCardLoading()
    }
}

@Composable
private fun H2hCardItem(
    response: H2hResponse
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 18.dp)
                ) {
                    Text(
                        text = unixToDateTimeSA(response.fixture.timestamp.toLong()) ?: "",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            fontFamily = QuickSandFont,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Start
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = response.fixture.venue.name,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                fontFamily = QuickSandFont,
                                color = Color.Black.copy(alpha = 0.25f)
                            ),
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = response.league.name,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                fontFamily = QuickSandFont,
                                color = Color.Black.copy(alpha = 0.25f)
                            ),
                            textAlign = TextAlign.End
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(gray)
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = response.teams.home.name,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black
                                    ),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = AppResource.getString(R.string.homeTeamTitle),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 8.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black.copy(alpha = 0.25f)
                                    ),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }

                    Text(
                        text = "${response.goals.home}:${response.goals.home}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            fontFamily = QuickSandFont,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center
                    )

                    Box {
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.End
                            ) {
                                Text(
                                    text = response.teams.away.name,
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black
                                    ),
                                    textAlign = TextAlign.End
                                )
                                Text(
                                    text = AppResource.getString(R.string.awayTitle),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 8.sp,
                                        fontFamily = QuickSandFont,
                                        color = Color.Black.copy(alpha = 0.25f)
                                    ),
                                    textAlign = TextAlign.End
                                )
                            }
                            Spacer(modifier = Modifier.padding(4.dp))
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(gray)
                            )
                        }
                    }

                }
            }

        }
    }
}

@Composable
private fun H2hCardLoading() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenWidthDp.dp * 0.3f)
            .padding(horizontal = 24.dp, vertical = 6.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        backgroundColor = Color.White
    ) {
        AnimatedShimmerTwoLines()
    }
}