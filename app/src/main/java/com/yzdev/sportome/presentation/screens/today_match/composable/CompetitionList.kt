package com.yzdev.sportome.presentation.screens.today_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.domain.model.MatchLeagueResponse
import com.yzdev.sportome.domain.model.MatchesResponseLocal
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun CompetitionList(
    item: MatchLeagueResponse,
    onClickShowMore: ()-> Unit,
    onClickItemMatch: (MatchesResponseLocal)-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row {
                Box(modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(gray)
                )

                Text(
                    text = " ${item.nameLeague}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        fontFamily = QuickSandFont
                    )
                )
            }

            Text(
                modifier = Modifier.clickable { onClickShowMore() },
                text = AppResource.getString(R.string.showMore),
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    fontFamily = QuickSandFont,
                    color = Color.Black.copy(alpha = 0.5f)
                ),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        item.listMatch.take(3).forEach {
            CompetitionItemDesign(item = it) {
                onClickItemMatch(it)
            }
        }

    }
}