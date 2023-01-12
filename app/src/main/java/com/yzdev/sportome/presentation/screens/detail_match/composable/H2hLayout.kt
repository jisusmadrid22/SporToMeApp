package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.data.remote.dto.match.MatchesForThisWeekDtoResponse
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun H2hLayout() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        LinearProgressStat(valueParent = 30f, valueHome = 15f)
        LinearProgressStat(valueParent = 3f, valueHome = 1f)
        LinearProgressStat(valueParent = 5f, valueHome = 2f)
        LinearProgressStat(valueParent = 16f, valueHome = 8f)
        LinearProgressStat(valueParent = 1f, valueHome = 0f)

        Spacer(modifier = Modifier.height(12.dp))

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

        H2hItemMatch()
        H2hItemMatch()
        H2hItemMatch()
        H2hItemMatch()
        H2hItemMatch()
        H2hItemMatch()

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
private fun H2hItemMatch(

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
                        text = "Date Match",
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
                            text = "Venue",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp,
                                fontFamily = QuickSandFont,
                                color = Color.Black.copy(alpha = 0.25f)
                            ),
                            textAlign = TextAlign.Start
                        )

                        Text(
                            text = "Name Competition",
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
                                    text = "Team 1",
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
                        text = "0:0",
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
                                    text = "Team 2",
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