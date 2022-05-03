package com.example.appnext.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.appnext.R
import com.example.appnext.databinding.ItemDailyResultBinding
import com.example.appnext.databinding.ItemMonthBinding
import com.example.appnext.models.ui.DailyInfoItem
import com.example.appnext.models.ui.Item
import com.example.appnext.models.ui.MonthItem
import com.example.appnext.ui.adapters.holders.DailyActivityViewHolder
import com.example.appnext.ui.adapters.holders.MonthViewHolder

class StateAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_month -> {
                val binding =
                    ItemMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)

                MonthViewHolder(binding)
            }
            R.layout.item_daily_result -> {

                val binding =
                    ItemDailyResultBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )

                DailyActivityViewHolder(binding)
            }
            else -> {
                throw RuntimeException("wrong viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val state = items[position]
        when (holder) {
            is DailyActivityViewHolder -> {
                holder.setState(state as DailyInfoItem)
            }
            is MonthViewHolder -> {
                holder.setState(state as MonthItem)
            }
            else -> {
                throw RuntimeException("not supported item type")
            }
        }
    }

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    override fun getItemCount(): Int = items.size

    fun setItems(numbers: List<Item>) {
        val callback = DiffUtils(oldItems = items, newItems = numbers)
        val result = DiffUtil.calculateDiff(callback)
        items.clear()
        items.addAll(numbers)
        result.dispatchUpdatesTo(this)
    }


}