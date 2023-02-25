package com.yzdev.sportome.presentation.screens.detail_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.R

@Composable
fun InfoTeams(
    homeTeam: String,
    awayTeam: String,
    formationHome: String?,
    formationAway: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
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
                        text = homeTeam,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = formationHome ?: AppResource.getString(R.string.homeTeamTitle),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black.copy(alpha = 0.25f)
                        ),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }

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
                        text = awayTeam,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            fontFamily = RobotoCondensed,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.End
                    )
                    Text(
                        text = formationAway ?: AppResource.getString(R.string.awayTitle),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            fontFamily = RobotoCondensed,
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