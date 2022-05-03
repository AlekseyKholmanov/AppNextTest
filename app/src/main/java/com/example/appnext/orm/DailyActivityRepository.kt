package com.example.appnext.orm

import com.example.appnext.models.domain.DailyActivityDTO
import com.example.appnext.models.domain.toDTO
import com.example.appnext.models.domain.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DailyActivityRepository(
    private val dailyActivityDao: DailyActivityDao
) {

    suspend fun saveAll(dailyTargets: List<DailyActivityDTO>) {
        dailyActivityDao.saveData(dailyTargets.map { it.toEntity() })
    }

    suspend fun clearAll(){
        dailyActivityDao.clearAll()
    }

    fun observeFavorite(): Flow<List<DailyActivityDTO>?> {
        return dailyActivityDao.observeAll()
            .map {
                if (it.isEmpty()) {
                    null
                } else
                    it.map { it.toDTO() }
            }
    }

}