@file:OptIn(ExperimentalMaterialApi::class)

package com.yzdev.sportome.common.composable.switchDesign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun SwitchType(
    isSelected: Boolean,
    isSelectedOnChange: (Boolean) -> Unit,
    onSizeChange: (IntSize) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(MaterialTheme.colors.primary)
            .onSizeChanged {
                onSizeChange(it)
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardSwitch(
                text = AppResource.getString(R.string.competition),
                isSelected = !isSelected,
                widthPercent = 0.5f,
                onClick = {
                    isSelectedOnChange(false)
                }
            )

            CardSwitch(
                text = AppResource.getString(R.string.team),
                isSelected = isSelected,
                widthPercent = 1f,
                onClick = {
                    isSelectedOnChange(true)
                }
            )
        }
    }
}

@Composable
private fun CardSwitch(
    text: String,
    isSelected: Boolean,
    widthPercent: Float,
    onClick: ()-> Unit
) {

    val color = if (isSelected) Color.White else MaterialTheme.colors.primary
    val colorText = if (isSelected) Color.Black else Color.White.copy(alpha = 0.5f)

    Card(
        modifier = Modifier
            .fillMaxWidth(widthPercent)
            .padding(vertical = 0.dp, horizontal = 4.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
        backgroundColor = color,
        onClick = {
            onClick()
        },
        enabled = !isSelected
    ) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    fontFamily = QuickSandFont,
                    color = colorText
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}