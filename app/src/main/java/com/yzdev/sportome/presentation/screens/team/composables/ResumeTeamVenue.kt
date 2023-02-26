package com.yzdev.sportome.presentation.screens.team.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines
import com.yzdev.sportome.domain.model.TeamInfoLocalResponse
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun ResumeTeamVenue(
    teamInfo: TeamInfoLocalResponse?
) {

    val modifierCustom = if (teamInfo != null) {
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    }else{
        Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenWidthDp.dp * 0.33f)
            .padding(horizontal = 24.dp)
    }

    Card(
        modifier = modifierCustom,
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        elevation = 0.dp
    ) {
        if (teamInfo != null){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.25f), contentAlignment = Alignment.Center) {
                    Box(modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(gray))
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = teamInfo.response.first().venue.name ?: "",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                fontFamily = RobotoCondensed,
                                color = Color.Black
                            )
                        )
                    }

                    Text(
                        text = stringResource(R.string.countryTitle) + ": ${teamInfo.response.first().team.country ?: ""}",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black
                        )
                    )

                    Text(
                        text = stringResource(R.string.cityTitle) + ": ${teamInfo.response.first().venue.city ?: ""}",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black
                        )
                    )

                    Text(
                        text = stringResource(R.string.capacityTitle) + ": ${teamInfo.response.first().venue.capacity ?: ""} " + stringResource(id = R.string.personTitle),
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 10.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black
                        )
                    )
                }
            }
        } else {
            Card(
                modifier = modifierCustom,
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                AnimatedShimmerTwoLines()
            }
        }
    }
}