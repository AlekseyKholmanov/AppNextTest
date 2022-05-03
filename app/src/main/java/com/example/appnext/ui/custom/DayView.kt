package com.example.appnext.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.appnext.databinding.CustomDayViewBinding

class DayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomDayViewBinding

    init {
        orientation = LinearLayout.VERTICAL
        binding = CustomDayViewBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setDate(date: String, dayOfWeek: String) {
        binding.dayNumber.text = date
        binding.dayText.text = dayOfWeek
    }
}