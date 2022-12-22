package com.yzdev.sportome.presentation.screens.tutorial.composables

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopHeaderTutorialOne(

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 24.dp, start = 24.dp, top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {


        }
    }
}

@Preview
@Composable
fun PreviewHeader() {
    TopHeaderTutorialOne()
}