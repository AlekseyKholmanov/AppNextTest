package com.example.appnext.models.network

import com.google.gson.annotations.SerializedName

class WeeklyDataResponseBody(

    @SerializedName("daily_data")
    val dailyData: DailyDataResponseBody,

    @SerializedName("daily_item")
    val dailyItem: DailyItemResponseBody
)


fun WeeklyDataResponseBody.toDtos(){

}
