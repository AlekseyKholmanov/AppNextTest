package com.example.appnext.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.appnext.models.ui.NetworkResult
import com.example.appnext.orm.DailyActivityRepository
import com.example.appnext.useCases.FetchWeeklyInfoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit

class InititalWorker(

    private val fetchWeeklyInfoUseCase: FetchWeeklyInfoUseCase,
    private val repository: DailyActivityRepository,
    private val params: WorkerParameters,
    private val appContext: Context,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) {
            val result = fetchWeeklyInfoUseCase.fetchWeeklyInfo()
            if (result is NetworkResult.Success) {
                repository.clearAll()
                repository.saveAll(result.data)
                executePeriodicWorker()
                Result.success()
            } else {
                Result.retry()
            }
        }


    private fun executePeriodicWorker() {
        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()

        dueDate.set(Calendar.HOUR_OF_DAY, 12)
        dueDate.set(Calendar.MINUTE, 0)
        dueDate.set(Calendar.SECOND, 0)
        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 12)
        }
        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
        val periodicWorker =
            PeriodicWorkRequestBuilder<PeriodicWorker>(12, TimeUnit.HOURS)
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .addTag("PERIODIC")
                .build()

        WorkManager.getInstance(applicationContext)
            .enqueue(periodicWorker)
    }
}