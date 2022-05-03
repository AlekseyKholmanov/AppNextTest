package com.example.appnext.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DailyActivityTable")
class DailyActivityEntity(
    @PrimaryKey
    val id: String,
    val dayOfWeek: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val goal: Int,
    val activity: Int,
    val meters: Int,
    val kCal: Int
)