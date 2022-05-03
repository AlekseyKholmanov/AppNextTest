package com.example.appnext.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.appnext.R
import com.example.appnext.databinding.CustomStepViewBinding

class DailyStepsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomStepViewBinding

    private val partialDoneColor = context.getColor(R.color.colorPrimaryBlue)
    private val doneColor = context.getColor(R.color.colorPrimaryGreen)

    init {
        orientation = HORIZONTAL
        binding = CustomStepViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setDailyResult(done: Int, target: Int) {
        with(binding) {
            val progress = done / target.toFloat() * 100
            if (done < target) {
                progressIndicator.setIndicatorColor(partialDoneColor)
                doneSteps.setTextColor(partialDoneColor)
            } else {
                progressIndicator.setIndicatorColor(doneColor)
                doneSteps.setTextColor(doneColor)
            }
            doneSteps.text = done.toString()
            targetSteps.text = target.toString()
            progressIndicator.progress = progress.toInt()
        }

    }
}