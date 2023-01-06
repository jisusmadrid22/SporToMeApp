package com.yzdev.sportome.ds.tutorialState

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first

class TutorialStateDs {
    /** edit tutorial state value*/
    suspend fun changeTutorialStateDs(keyTutorial: String, value: Boolean, dataStore: DataStore<Preferences>){
        val dataStoreKey = booleanPreferencesKey(keyTutorial)
        dataStore.edit {state->
            state[dataStoreKey] = value
        }
    }

    /** get value tutorial state ds*/
    suspend fun getTutorialStateDs(dataStore: DataStore<Preferences>, keyTutorial: String): Boolean?{
        val dataStoreKey = booleanPreferencesKey(keyTutorial)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
}
