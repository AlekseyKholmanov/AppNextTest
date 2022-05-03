package com.example.appnext.di

import androidx.room.Room
import com.example.appnext.orm.AppDatabase
import com.example.appnext.orm.DailyActivityRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    val DATABASE_NAME = "appnext_database"

    single {
        Room.databaseBuilder(androidApplication(), AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDatabase>().dailyTargetsDao()
    }

    single {
        DailyActivityRepository(
            dailyActivityDao = get()
        )
    }
}
