@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.presentation.screens.team.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed

@Composable
fun SelectorTeamInfo(
    numberSelector: Int,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CardSelector(isSelected = numberSelector == 1, title = AppResource.getString(R.string.resume)) {
            onClick(1)
        }

        Spacer(modifier = Modifier.width(4.dp))

        CardSelector(isSelected = numberSelector == 2, title = AppResource.getString(R.string.statsTitle)) {
            onClick(2)
        }
    }
}

@Composable
private fun CardSelector(
    isSelected: Boolean,
    title: String,
    onClick: ()-> Unit
) {
    Card(
        backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.White,
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary),
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp,
        onClick = {
            onClick()
        }
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
            text = title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                fontFamily = RobotoCondensed,
                color = if (!isSelected) MaterialTheme.colors.primary else Color.White
            ),
            textAlign = TextAlign.Center
        )
    }
}