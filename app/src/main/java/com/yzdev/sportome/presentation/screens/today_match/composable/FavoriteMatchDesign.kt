@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.today_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.domain.model.MatchesResponseLocal
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.presentation.ui.theme.greenSuccess

@Composable
fun FavoriteMatchDesign(
    item: MatchesResponseLocal,
    onClick: (MatchesResponseLocal)-> Unit
) {

    val width = LocalConfiguration.current.screenWidthDp.dp

    Card(
        modifier = Modifier
            .width(width * 0.85f)
            .heightIn(min = width * 0.2f)
            .padding(horizontal = 16.dp),
        elevation = 0.dp,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = MaterialTheme.colors.primary,
        onClick = {
            onClick(item)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.fixture.venue.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                    fontFamily = RobotoCondensed
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.padding(top = 6.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TeamInfoMatchDesign(item = item.teams.home)

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${item.goals.home ?: 0}:${item.goals.away ?: 0}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            fontFamily = RobotoCondensed
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.padding(top = 4.dp))

                    Box(
                        modifier = Modifier.border(width = 1.dp, color = greenSuccess, shape = RoundedCornerShape(12.dp)).clip(RoundedCornerShape(12.dp)).background(Color.Transparent)
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            text = "${item.fixture.status.elapsed}'",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp,
                                fontFamily = RobotoCondensed,
                                color = greenSuccess
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                TeamInfoMatchDesign(item = item.teams.away)
            }

            Spacer(modifier = Modifier.padding(top = 6.dp))
        }
    }
}

@Composable
fun TeamInfoMatchDesign(
    item: MatchesResponseLocal.Teams.Home
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(gray))

        Spacer(modifier = Modifier.padding(top = 4.dp))

        Text(
            text = item.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                fontFamily = RobotoCondensed
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = AppResource.getString(R.string.homeTeamTitle),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 6.sp,
                fontFamily = RobotoCondensed,
                color = Color.White.copy(alpha = 0.5f)
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TeamInfoMatchDesign(
    item: MatchesResponseLocal.Teams.Away
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(gray))

        Spacer(modifier = Modifier.padding(top = 4.dp))

        Text(
            text = item.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                fontFamily = RobotoCondensed
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = AppResource.getString(R.string.awayTitle),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 6.sp,
                fontFamily = RobotoCondensed,
                color = Color.White.copy(alpha = 0.5f)
            ),
            textAlign = TextAlign.Center
        )
    }
}