package com.example.appnext.useCases

import android.util.Log
import com.example.appnext.models.domain.DailyActivityDTO
import com.example.appnext.models.network.toDTOs
import com.example.appnext.models.ui.NetworkResult
import com.example.appnext.network.WeeklyActivityApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchWeeklyInfoUseCaseImpl(
    private val api: WeeklyActivityApi
):FetchWeeklyInfoUseCase {
    override suspend fun fetchWeeklyInfo(): NetworkResult<List<DailyActivityDTO>> {
        return withContext(Dispatchers.IO) {
            val response = api.fetchWeeklyData()
            val data = response.body()
            Log.d("M_APP","Fetch news frow server")
            return@withContext if (response.isSuccessful && data != null) {
                val mapper = data.toDTOs()
                NetworkResult.Success(mapper)
            } else {
                //There we can handle error and pass correct message
                NetworkResult.Error("")
            }
        }
    }
}