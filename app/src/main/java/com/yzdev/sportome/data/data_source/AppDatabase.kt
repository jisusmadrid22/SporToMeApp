package com.yzdev.sportome.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yzdev.sportome.domain.model.*

@Database(
    entities = [LocalCountry::class, LocalCompetition::class, LocalSeasons::class, LocalTeam::class, LocalMatch::class, LocalSeasonPlayer::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val appDao: AppDao

    companion object {
        const val DATABASE_NAME = "sportome_db"
    }
}