package com.yzdev.sportome.common.composable.itemListDesign

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.yzdev.sportome.common.composable.canvasUtils.AnimatedShimmerTwoLines

@Composable
fun CardLoadingFullWidth(

) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(LocalConfiguration.current.screenWidthDp.dp * 0.35f)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp
    ) {
        Column {
            AnimatedShimmerTwoLines()
        }
    }
}