package com.yzdev.sportome.presentation.screens.team.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.getFirstAndLastName
import com.yzdev.sportome.domain.model.TeamSquadLocalResponse
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed
import com.yzdev.sportome.presentation.ui.theme.blackLight
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun LineupPlayerItem(
    player: TeamSquadLocalResponse.Response.Player
) {

    val names = getFirstAndLastName(player.name ?: "")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .background(gray)
                )

                Spacer(modifier = Modifier.width(4.dp))

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
                            append("${player.number ?: 0}. ${names.first} ")
                        }
                        if (names.second.isNotEmpty()){
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp,
                                    fontFamily = RobotoCondensed,
                                    color = Color.Black.copy(alpha = 0.5f),
                                )
                            ){
                                append(names.second)
                            }
                        }
                    },
                    textAlign = TextAlign.Start
                )
            }

            Text(
                text = player.position ?: "",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                    fontFamily = RobotoCondensed,
                    color = Color.Black.copy(alpha = 0.25f)
                ),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .padding(horizontal = 12.dp)
            .background(Color.Black.copy(alpha = 0.25f))
        )
    }
}