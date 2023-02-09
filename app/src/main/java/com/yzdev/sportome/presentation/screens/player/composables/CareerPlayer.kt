package com.yzdev.sportome.presentation.screens.player.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
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
import com.yzdev.sportome.common.AutoResizedText
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.common.unixToDateTimeSA
import com.yzdev.sportome.domain.model.H2hResponse
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun CareerPlayer(

) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(){
            items(10){
                CareerPlayerCard(item = "team")
            }
        }
    }
}

@Composable
private fun CareerPlayerCard(
    item: String?
) {
    if (item != null){
        CareerItem()
    }else{
        CareerLoading()
    }
}

@Composable
private fun CareerItem(

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
                        .padding(vertical = 8.dp, horizontal = 18.dp)
                ) {
                    Text(
                        text = "Date",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Start
                    )
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
                                    text = stringResource(id = R.string.out),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 8.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black.copy(alpha = 0.25f)
                                    ),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = "Team",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black
                                    ),
                                    textAlign = TextAlign.Start
                                )
                            }
                        }
                    }

                    Text(
                        text = "Precio",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            fontFamily = RobotoCondensed,
                            color = MaterialTheme.colors.secondary
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
                                    text = stringResource(id = R.string.inTeam),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 8.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black.copy(alpha = 0.25f)
                                    ),
                                    textAlign = TextAlign.Start
                                )
                                Text(
                                    text = "Team",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        fontFamily = RobotoCondensed,
                                        color = Color.Black
                                    ),
                                    textAlign = TextAlign.Start
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
private fun CareerLoading() {
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