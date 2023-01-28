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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.get12HourFormatBy24HourFormat
import com.yzdev.sportome.common.getHourByDateUnix
import com.yzdev.sportome.common.getHourNumberByUnix
import com.yzdev.sportome.domain.model.MatchesResponseLocal
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray
import com.yzdev.sportome.presentation.ui.theme.greenSuccess

@Composable
fun CompetitionItemDesign(
    item: MatchesResponseLocal,
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
            onClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp * 0.3f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.teams.home.name,
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
            }

            Spacer(modifier = Modifier.padding(spacer))

            Box(
                modifier = Modifier
                    .border(width = 1.dp, color = greenSuccess, shape = RoundedCornerShape(12.dp))
                    .clip(
                        RoundedCornerShape(12.dp)
                    )
                    .background(Color.Transparent)
            ) {

                if (item.fixture.status.elapsed != null){
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        text = "${item.fixture.status.elapsed}'",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            fontFamily = QuickSandFont,
                            color = greenSuccess
                        ),
                        textAlign = TextAlign.Center
                    )
                }else{
                    Text(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        text = "${get12HourFormatBy24HourFormat(getHourByDateUnix(item.fixture.timestamp), hour = getHourNumberByUnix(item.fixture.timestamp).toInt())}",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            fontFamily = QuickSandFont,
                            color = greenSuccess
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.padding(spacer))

            Row(
                modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp * 0.3f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(gray))

                Spacer(modifier = Modifier.padding(spacer))

                Text(
                    text = item.teams.away.name,
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
}