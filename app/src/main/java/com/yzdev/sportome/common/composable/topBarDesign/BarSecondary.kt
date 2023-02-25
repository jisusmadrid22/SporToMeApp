package com.yzdev.sportome.common.composable.topBarDesign

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BarSecondary(
    showStartButton: Boolean = false,
    iconStartButton: ImageVector? = null,
    actionStartButton: ()-> Unit = {},
    showEndButton: Boolean = false,
    iconEndButton: ImageVector? = null,
    actionEndButton: ()-> Unit = {},
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if ((showStartButton) and (iconStartButton != null)){
            IconButton(onClick = { actionStartButton() }) {
                iconStartButton?.let { Icon(imageVector = it, contentDescription = "") }
            }
        }else{
            Spacer(modifier = Modifier.padding(start = 24.dp, bottom = 18.dp, top = 18.dp))
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            content = content
        )

        if ((showEndButton) and (iconStartButton != null)){
            IconButton(onClick = { actionEndButton() }) {
                iconEndButton?.let { Icon(imageVector = it, contentDescription = "") }
            }
        }else{
            if((showStartButton) and (iconStartButton != null)){
                Spacer(modifier = Modifier.padding(end = 48.dp, bottom = 18.dp, top = 18.dp))
            }else{
                Spacer(modifier = Modifier.padding(end = 24.dp, bottom = 18.dp, top = 18.dp))
            }
        }
    }
}