package com.example.appnext.useCases

import com.example.appnext.models.domain.DailyActivityDTO
import kotlinx.coroutines.flow.Flow

interface WeeklyActivityInfoUseCase {

    fun observeWeeklyActivity(): Flow<List<DailyActivityDTO>>

    suspend fun saveAll(activity: List<DailyActivityDTO>)
}