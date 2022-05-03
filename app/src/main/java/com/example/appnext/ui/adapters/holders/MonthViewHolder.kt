package com.example.appnext.ui.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.example.appnext.databinding.ItemMonthBinding
import com.example.appnext.models.ui.MonthItem

class MonthViewHolder(
    private val binding: ItemMonthBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setState(item: MonthItem) {
        with(binding) {
            monthHeader.text = item.month
        }
    }
}
