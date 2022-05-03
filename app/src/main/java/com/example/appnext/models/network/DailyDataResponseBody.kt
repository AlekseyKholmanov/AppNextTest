package com.example.appnext.models.network

import com.google.gson.annotations.SerializedName

class DailyDataResponseBody(

    @SerializedName("daily_distance_meters")
    val meters: Int,

    @SerializedName("daily_kcal")
    val kcal: Int
)