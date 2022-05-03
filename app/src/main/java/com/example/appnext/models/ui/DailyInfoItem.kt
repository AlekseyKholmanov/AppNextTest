package com.example.appnext.models.ui

import com.example.appnext.R
import java.util.*

data class DailyInfoItem(
    val id: String = UUID.randomUUID().toString(),
    val dayOfWeek: Int,
    val dayOfMonth: Int,
    val goal: Int,
    val activity: Int,
    val meters: Int,
    val kCal: Int
) : Item {

    override fun getViewType(): Int = R.layout.item_daily_result
}
