package com.yzdev.sportome.common.composable.topBarDesign

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun TopBarCustomApp(
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
            Spacer(modifier = Modifier.padding(24.dp))
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
            Spacer(modifier = Modifier.padding(24.dp))
        }
    }
}