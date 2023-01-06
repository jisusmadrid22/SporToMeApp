package com.yzdev.sportome

import android.app.Application
import android.content.Context
import android.view.KeyCharacterMap.KeyData
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.yzdev.sportome.ds.DataStoreNames
import com.yzdev.sportome.ds.KeysDataStore
import com.yzdev.sportome.ds.tutorialState.TutorialStateDs
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.TutorialDs: DataStore<Preferences> by preferencesDataStore(name = DataStoreNames.TutorialDs.nameDataStore)

@HiltAndroidApp
class App: Application() {
    companion object{
        lateinit var instance: App private set
        var isTutorial: Boolean? = false
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        CoroutineScope(Dispatchers.IO).launch {
            /** tutorial state*/
            isTutorial = TutorialStateDs().getTutorialStateDs(
                dataStore = TutorialDs,
                keyTutorial = KeysDataStore.KeyTutorialDs.keyName
            )

            if (isTutorial == null){
                /** data store tutorial not exist, creating...*/
                TutorialStateDs().changeTutorialStateDs(
                    keyTutorial = KeysDataStore.KeyTutorialDs.keyName,
                    value = false,
                    dataStore = TutorialDs
                )
                isTutorial = TutorialStateDs().getTutorialStateDs(
                    dataStore = TutorialDs,
                    keyTutorial = KeysDataStore.KeyTutorialDs.keyName
                ) ?: false
            }

            /**************************************************/
        }
    }
}