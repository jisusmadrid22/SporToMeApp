package com.yzdev.sportome.presentation.screens.player

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.yzdev.sportome.presentation.screens.player.composables.HeaderPlayer
import com.yzdev.sportome.presentation.ui.theme.grayBackground

@Composable
fun PlayerInfoScreen() {
    PlayerInfoLayout()
}

@Composable
private fun PlayerInfoLayout() {
    Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = grayBackground) {
        Column(modifier = Modifier.fillMaxSize()) {
            HeaderPlayer()
        }
    }
}