package com.yzdev.sportome.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yzdev.sportome.domain.model.LocalCompetition
import com.yzdev.sportome.domain.model.LocalCountry
import com.yzdev.sportome.domain.model.LocalSeasons
import com.yzdev.sportome.domain.model.LocalTeam

@Database(
    entities = [LocalCountry::class, LocalCompetition::class, LocalSeasons::class, LocalTeam::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val appDao: AppDao

    companion object {
        const val DATABASE_NAME = "sportome_db"
    }
}