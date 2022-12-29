package com.yzdev.sportome.presentation.screens.today_match.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.yzdev.sportome.common.Competition
import com.yzdev.sportome.common.TeamMatch
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont
import com.yzdev.sportome.presentation.ui.theme.gray

@Composable
fun CompetitionList(
    item: Competition,
    onClickShowMore: ()-> Unit,
    onClickItemMatch: (TeamMatch)-> Unit
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
                    text = " ${item.name}",
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
                    fontSize = 8.sp,
                    fontFamily = QuickSandFont,
                    color = Color.Black.copy(alpha = 0.5f)
                ),
                textAlign = TextAlign.End
            )
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        item.matches.take(3).forEach {
            CompetitionItemDesign(item = it) {
                onClickItemMatch(it)
            }
        }

    }
}