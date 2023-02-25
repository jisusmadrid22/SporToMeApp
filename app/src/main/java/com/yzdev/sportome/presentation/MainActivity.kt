package com.yzdev.sportome.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.yzdev.sportome.TutorialDs
import com.yzdev.sportome.common.AppResource
import com.yzdev.sportome.ds.KeysDataStore
import com.yzdev.sportome.ds.tutorialState.TutorialStateDs
import com.yzdev.sportome.presentation.ui.theme.SporToMeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var isNotTutorial by remember{ mutableStateOf(false) }

            LaunchedEffect(key1 = true){
                isNotTutorial = TutorialStateDs().getTutorialStateDs(
                    dataStore = AppResource.getAppContext().TutorialDs,
                    keyTutorial = KeysDataStore.KeyTutorialDs.keyName
                ) ?: false
            }

            SporToMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation(
                        isNotTutorial = isNotTutorial
                    )
                }
            }
        }
    }
}