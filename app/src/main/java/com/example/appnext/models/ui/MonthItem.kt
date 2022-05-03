package com.example.appnext.models.ui

import com.example.appnext.R

data class MonthItem(
    val month: String
):Item {
    override fun getViewType(): Int = R.layout.item_month
}
