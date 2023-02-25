package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yzdev.sportome.common.labelTextFieldByStep
import com.yzdev.sportome.presentation.ui.theme.RobotoCondensed

@Composable
fun TextFieldTutorial(
    value: String,
    numberStep: Int,
    background: Color = Color.White,
    placeholder: String = labelTextFieldByStep(numberStep),
    textOnChange: (String)-> Unit
) {

    val focus = LocalFocusManager.current

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            textOnChange(it)
        },
        textStyle = TextStyle(
            fontWeight = FontWeight.Light,
            fontSize = 14.sp,
            fontFamily = RobotoCondensed
        ),
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    fontFamily = RobotoCondensed
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
            backgroundColor = background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {focus.clearFocus(force = true)})
    )
}