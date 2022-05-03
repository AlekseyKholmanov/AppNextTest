package com.example.appnext.useCases

import com.example.appnext.models.domain.DailyActivityDTO
import com.example.appnext.models.ui.NetworkResult
import com.example.appnext.orm.DailyActivityRepository
import kotlinx.coroutines.flow.*

class WeeklyActivityInfoUseCaseImpl(
    private val fetchWeeklyInfoUseCase: FetchWeeklyInfoUseCase,
    private val db: DailyActivityRepository
) : WeeklyActivityInfoUseCase {

    override fun observeWeeklyActivity(): Flow<List<DailyActivityDTO>> {
        val dataFromDB = db.observeFavorite()
        return dataFromDB.flatMapLatest {
            if (it.isNullOrEmpty()) {
                val result = fetchWeeklyInfoUseCase.fetchWeeklyInfo()
                when (result) {
                    is NetworkResult.Error -> {
                        emptyFlow()
                    }
                    is NetworkResult.Success -> {
                        db.saveAll(result.data)
                        emptyFlow()
                    }
                }
            } else {
                flowOf(it)
            }
        }
    }

    override suspend fun saveAll(activity: List<DailyActivityDTO>) {}
}