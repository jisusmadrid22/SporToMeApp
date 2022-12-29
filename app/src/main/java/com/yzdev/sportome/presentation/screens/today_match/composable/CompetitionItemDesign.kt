@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.today_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import com.yzdev.sportome.common.Competition
import com.yzdev.sportome.common.TeamMatch
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.presentation.ui.theme.greenSuccess

@Composable
fun CompetitionItemDesign(
    item: TeamMatch,
    onClick: ()-> Unit
) {

    val spacer = 2.dp

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = Color.White,
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp),
        onClick = {

        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = item.teams.first().nameTeam,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                    fontFamily = QuickSandFont,
                ),
                textAlign = TextAlign.End
            )

            Spacer(modifier = Modifier.padding(spacer))

            Box(modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(gray))

            Spacer(modifier = Modifier.padding(spacer))

            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = greenSuccess, shape = RoundedCornerShape(12.dp))
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(Color.Transparent)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    text = item.time,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 6.sp,
                        fontFamily = QuickSandFont,
                        color = greenSuccess
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.padding(spacer))

            Box(modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(gray))

            Spacer(modifier = Modifier.padding(spacer))

            Text(
                text = item.teams.last().nameTeam,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                    fontFamily = QuickSandFont
                ),
                textAlign = TextAlign.Start
            )
        }
    }
}