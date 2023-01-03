package com.yzdev.sportome.ds

sealed class DataStoreNames(val nameDataStore: String){
    object TutorialDs: DataStoreNames("Tutorial")
}

sealed class KeysDataStore(val keyName: String){
    object KeyTutorialDs: KeysDataStore("isTutorial")
}