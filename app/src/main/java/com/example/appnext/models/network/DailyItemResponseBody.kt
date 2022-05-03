package com.example.appnext.models.network

import com.google.gson.annotations.SerializedName

class DailyItemResponseBody(
    @SerializedName("daily_goal")
    val goal: Int,

    @SerializedName("daily_activity")
    val activity: Int
)