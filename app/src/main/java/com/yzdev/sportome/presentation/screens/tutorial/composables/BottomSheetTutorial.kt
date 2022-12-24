package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.R
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun BottomSheetTutorialDesign(
    numberStep: Int,
    clickEvent: ()-> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        elevation = 0.dp
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 14.dp),
            onClick = {
                clickEvent()
            },
            shape = RoundedCornerShape(12.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp, pressedElevation = 0.dp, focusedElevation = 0.dp, hoveredElevation = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            )
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = if (numberStep < 4) AppResource.getString(R.string.continueTitle) else AppResource.getString(R.string.finishTitle),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    fontFamily = QuickSandFont
                )
            )
        }
    }
}