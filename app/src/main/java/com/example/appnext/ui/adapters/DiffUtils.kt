package com.example.appnext.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.appnext.models.ui.DailyInfoItem
import com.example.appnext.models.ui.Item
import com.example.appnext.models.ui.MonthItem

class DiffUtils(val oldItems: List<Item>, val newItems: List<Item>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldItems[oldItemPosition]
        val new = newItems[newItemPosition]
        return when {
            old is MonthItem && new is MonthItem -> true
            old is DailyInfoItem && new is DailyInfoItem -> true
            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldItems[oldItemPosition]
        val new = newItems[newItemPosition]
        return when {

            old is MonthItem && new is MonthItem ||
                    old is DailyInfoItem && new is DailyInfoItem -> {
                old == new
            }
            else -> false
        }
    }


}