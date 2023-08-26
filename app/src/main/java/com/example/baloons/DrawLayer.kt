package com.example.baloons

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue
import android.view.View
import java.util.*

class DrawLayer(context: Context) : View(context) {

    private val paint = Paint()
    private val random = Random()
    private val randomCount = random.nextInt(10)

    private var radius = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 10F, resources.displayMetrics
    )

    private val width = context.resources.displayMetrics.widthPixels
    private val height = context.resources.displayMetrics.heightPixels

    // Первая точка
    private var x0: Float = random.nextInt(width).toFloat()
    private var y0: Float = random.nextInt(height).toFloat()

    // Вторая точка
    private var x1: Float = random.nextInt(width).toFloat()
    private var y1: Float = random.nextInt(height).toFloat()

    // Ищем k (угол) и b (шаг)
    private var y = y1 - y0
    private var x = x1 - x0
    private var k: Float = (
            if (x != 0.0f)
                y / x
            else
                0.0f
            )
    //private var b = y0 - k * x0 // получается, не нужен?

    private var xShift = 5
    private var yShift = 5
    private var xCur = x1
    private var yCur = y1

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(
            context.resources.getColor(R.color.white, context.resources.newTheme())
        )

        if (xCur <= 0 + radius || xCur >= width - radius) {
            k *= -1
            xShift *= -1
        }

        if (yCur <= 0 + radius || yCur >= height - radius * 3) {
            k *= -1
            yShift *= -1
        }

        xCur += xShift
        yCur += yShift

        paint.color = context.resources.getColor(R.color.black, context.resources.newTheme())
        canvas?.drawCircle(xCur, yCur, radius, paint)

        postInvalidate()
    }
}
