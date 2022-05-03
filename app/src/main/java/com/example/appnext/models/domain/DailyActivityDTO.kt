package com.example.appnext.models.domain

import com.example.appnext.models.database.DailyActivityEntity
import java.util.*

data class DailyActivityDTO(
    val id: String = UUID.randomUUID().toString(),
    val dayOfWeek: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val goal: Int,
    val activity: Int,
    val meters: Int,
    val kCal: Int
)


fun DailyActivityDTO.toEntity(): DailyActivityEntity {
    return DailyActivityEntity(
        id = id,
        dayOfWeek = dayOfWeek,
        dayOfMonth = dayOfMonth,
        month = month,
        year = year,
        goal = goal,
        activity = activity,
        meters = meters,
        kCal = kCal,
    )
}

fun DailyActivityEntity.toDTO(): DailyActivityDTO {
    return DailyActivityDTO(
        id = id,
        dayOfWeek = dayOfWeek,
        dayOfMonth = dayOfMonth,
        month = month,
        year = year,
        goal = goal,
        activity = activity,
        meters = meters,
        kCal = kCal,
    )
}
