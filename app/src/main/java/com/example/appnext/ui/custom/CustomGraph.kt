package com.example.appnext.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.appnext.R
import com.example.appnext.extensions.dp
import com.example.appnext.extensions.getDayName
import com.example.appnext.models.ui.DailyInfoItem

class CustomGraph @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val activityColor = context.getColor(R.color.colorPrimaryBlue)
    private val goalColor = context.getColor(R.color.colorPrimaryGreen)

    private val texColor = context.getColor(R.color.colorTextMedium)
    private val bottomGraphMargin = 52.dp()

    private val donePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = activityColor
        style = Paint.Style.FILL
    }

    private val activityPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = goalColor
        style = Paint.Style.FILL
    }

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = texColor
        textSize = 12.dp()
        textAlign = Paint.Align.CENTER
        isAntiAlias = true
    }
    private val path = Path()


    init {
        minimumWidth = 312.dp().toInt()
        minimumHeight = 213.dp().toInt()
    }

    private var data = mutableListOf<DailyInfoItem>()
    private var max = 0f
    private val topPadding = 20.dp()
    private val topRadius = 6.dp()

    fun setData(items: List<DailyInfoItem>) {
        if (items.isNotEmpty()) {
            data.clear()
            data.addAll(items)
            max = items.maxOf { it.activity }.toFloat()
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val availalableHeight = height - topPadding - bottomGraphMargin
        val yStart = height - bottomGraphMargin
        val spaceBetween = (measuredWidth - (2 * 12.dp() * 7f)) / 6
        val topText = height - 14.dp() - 21.dp()
        var currentX = 0f

        data.forEach {

            val activeMultiplier = it.activity / max
            val goalMultiplier = it.goal / max
            val topLine =
                topPadding + topRadius + (availalableHeight - availalableHeight * activeMultiplier)

            //draw dayOfWeek
            val text = getDayName(it.dayOfWeek)
            val textWidth = textPaint.measureText(text)
            canvas.drawText(
                text,
                currentX + textWidth / 2,
                topText.toInt() - textPaint.ascent(),
                textPaint
            )

            //draw ActivityGraph
            val goalTopline =
                topPadding + topRadius + (availalableHeight - availalableHeight * goalMultiplier)
            path.moveTo(currentX, yStart)
            path.lineTo(currentX, topLine)
            currentX += 12.dp()
            path.lineTo(currentX, topLine)
            path.lineTo(currentX, yStart)
            canvas.drawCircle(currentX - 6.dp(), topLine, 6.dp(), donePaint)
            canvas.drawPath(path, donePaint)
            //draw dailyGoalGraph

            path.reset()
            path.moveTo(currentX, yStart)
            path.lineTo(currentX, goalTopline)
            currentX += 12.dp()
            path.lineTo(currentX, goalTopline)
            path.lineTo(currentX, yStart)
            canvas.drawPath(path, activityPaint)
            canvas.drawCircle(currentX - 6.dp(), goalTopline, 6.dp(), activityPaint)
            path.reset()

            currentX += spaceBetween
        }
        drawTodayIndicator(canvas)
    }


    private fun drawTodayIndicator(canvas: Canvas) {
        val rect = Rect(width - 24.dp().toInt(), height - 4.dp().toInt(), width, height)
        canvas.drawRect(rect, donePaint)
    }
}