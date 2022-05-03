package com.example.appnext.ui.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.appnext.databinding.ItemDailyResultBinding
import com.example.appnext.extensions.getDayName
import com.example.appnext.models.ui.DailyInfoItem

class DailyActivityViewHolder(
    private val binding: ItemDailyResultBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setState(item: DailyInfoItem) {
        with(binding) {
            stepsResult.setDailyResult(done = item.activity, target = item.goal)
            distanceView.setDistance(
                kcal = item.kCal,
                distance = item.meters,
                isCompleteTarget = item.activity > item.goal
            )
            dayView.setDate(date = item.dayOfMonth.toString(), dayOfWeek = getDayName(item.dayOfWeek))
        }
    }
}
