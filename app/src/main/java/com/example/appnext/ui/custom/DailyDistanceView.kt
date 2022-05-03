package com.example.appnext.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.appnext.R
import com.example.appnext.databinding.CustomDistanceViewBinding
import com.example.appnext.extensions.dp

class DailyDistanceView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: CustomDistanceViewBinding

    private val partialDoneColor = context.getColor(R.color.colorLight)
    private val doneColor = context.getColor(R.color.colorPrimaryGreen)

    init {
        orientation = VERTICAL
        minimumWidth = 102.dp().toInt()
        binding = CustomDistanceViewBinding.inflate(LayoutInflater.from(context), this)
    }

    //TODO getStrings from resources
    fun setDistance(kcal: Int, distance: Int, isCompleteTarget: Boolean) {
        binding.KcalValue.text = kcal.toString()
        val (realDistance: Int, measurement: String) = if (distance < 1000) {
            distance to " M"
        } else {
            distance / 1000 to " KM"
        }
        binding.distance.text = realDistance.toString()
        binding.distanceMeasurement.text = measurement
        if (isCompleteTarget) {
            binding.distanceIndicator.imageTintList = ColorStateList.valueOf(doneColor)
            binding.kcalIndicator.imageTintList = ColorStateList.valueOf(doneColor)
        } else {
            binding.distanceIndicator.imageTintList = ColorStateList.valueOf(partialDoneColor)
            binding.kcalIndicator.imageTintList = ColorStateList.valueOf(partialDoneColor)
        }

    }
}