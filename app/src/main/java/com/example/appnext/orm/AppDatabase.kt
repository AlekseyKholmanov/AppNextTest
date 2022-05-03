package com.example.appnext.orm

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appnext.models.database.DailyActivityEntity

@Database(
    entities = [
        DailyActivityEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dailyTargetsDao(): DailyActivityDao
}
