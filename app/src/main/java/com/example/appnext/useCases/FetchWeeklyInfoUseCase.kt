package com.example.appnext.useCases

import com.example.appnext.models.domain.DailyActivityDTO
import com.example.appnext.models.ui.NetworkResult

interface FetchWeeklyInfoUseCase {
    suspend fun fetchWeeklyInfo(): NetworkResult<List<DailyActivityDTO>>
}