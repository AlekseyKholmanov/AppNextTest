package com.example.appnext.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.appnext.models.ui.NetworkResult
import com.example.appnext.orm.DailyActivityRepository
import com.example.appnext.useCases.FetchWeeklyInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class PeriodicWorker(
    private val fetchWeeklyInfoUseCase: FetchWeeklyInfoUseCase,
    private val repository: DailyActivityRepository,
    val ctx: Context,
    private val params: WorkerParameters
) : Worker(ctx, params) {
    override fun doWork(): Result = runBlocking {
        withContext(Dispatchers.IO) {
            val result = fetchWeeklyInfoUseCase.fetchWeeklyInfo()
            if (result is NetworkResult.Success) {
                repository.clearAll()
                repository.saveAll(result.data)
                Result.success()
            } else {
                Result.failure()
            }
        }
    }

}