package com.example.appnext.network

import com.example.appnext.models.network.WeeklyResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface WeeklyActivityApi {

    @GET("daily_items")
    suspend fun fetchWeeklyData(): Response<WeeklyResponseBody>
}