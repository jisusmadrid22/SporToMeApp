package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.labelTextFieldByStep
import com.yzdev.sportome.common.titleTutorialByStep
import com.yzdev.sportome.presentation.ui.theme.QuickSandFont

@Composable
fun TextFieldTutorial(
    value: String,
    numberStep: Int,
    textOnChange: (String)-> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            textOnChange(it)
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            fontFamily = QuickSandFont
        ),
        label = {
            Text(
                text = labelTextFieldByStep(numberStep),
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    fontFamily = QuickSandFont
                )
            )
        },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = "")
        },
        shape = RoundedCornerShape(12.dp),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = MaterialTheme.colors.primary,
            unfocusedIndicatorColor = MaterialTheme.colors.primary
        )
    )
}