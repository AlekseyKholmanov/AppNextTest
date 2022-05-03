package com.example.appnext.models.network

import com.example.appnext.models.domain.DailyActivityDTO
import com.google.gson.annotations.SerializedName
import org.joda.time.Instant

class WeeklyResponseBody(

    @SerializedName("weekly_data")
    val weeklyData: List<WeeklyDataResponseBody>,

    @SerializedName("err")
    val error: Int
)


fun WeeklyResponseBody.toDTOs(): List<DailyActivityDTO> {
    Instant.now().millis
    val today = Instant.now().toDateTime()
    return this.weeklyData.mapIndexed { index, weeklyData ->
        val date = today.minusDays(this.weeklyData.size - index - 1)
        DailyActivityDTO(
            dayOfWeek = date.dayOfWeek,
            dayOfMonth = date.dayOfMonth,
            month = date.monthOfYear,
            year = date.year,
            goal = weeklyData.dailyItem.goal,
            activity = weeklyData.dailyItem.activity,
            meters = weeklyData.dailyData.meters,
            kCal = weeklyData.dailyData.kcal
        )
    }
}