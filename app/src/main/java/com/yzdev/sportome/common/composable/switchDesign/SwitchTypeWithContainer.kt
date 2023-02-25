package com.yzdev.sportome.common.composable.switchDesign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun SwitchTypeWithContainer(
    isSelected: Boolean,
    isSelectedOnChange: (Boolean)-> Unit
) {

    val sizeSwitch = remember {
        mutableStateOf(IntSize.Zero)
    }

    Box(modifier = Modifier.fillMaxWidth()) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(sizeSwitch.value.height.dp * 0.2f)
            .background(Color.White))

        SwitchType(
            isSelected = isSelected,
            isSelectedOnChange = {
                isSelectedOnChange(it)
            }
        ){
            sizeSwitch.value = it
        }
    }
}