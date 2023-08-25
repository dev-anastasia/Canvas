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

    private var radius = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 10F, resources.displayMetrics
    )

    private val width = context.resources.displayMetrics.widthPixels
    private val height = context.resources.displayMetrics.heightPixels

    // Первая точка
    private val x0 = random.nextInt(width).toFloat()
    private val y0 = random.nextInt(height).toFloat()

    // Вторая точка
    private val x1 = random.nextInt(width).toFloat()
    private val y1 = random.nextInt(height).toFloat()

    // Ищем k (угол) и b (шаг)
    private val y = y1 - y0
    private val x = x1 - x0
    private var k: Float = y / x
    private var b = y0 - k * x0

    private var shift = 5
    private var xCur = x1
    private var yCur: Float = y1


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawColor(
            context.resources.getColor(R.color.white, context.resources.newTheme())
        )
        paint.color = context.resources.getColor(R.color.black, context.resources.newTheme())

        if (yCur <= 0 || yCur >= height || xCur <= 0 || xCur >= width) {
            k *= -1
            shift *= -1
            b = 2 * k * xCur + b
        }
        xCur += shift
        yCur = k * xCur + b

        canvas?.drawCircle(xCur, yCur, radius, paint)

        postInvalidate()
    }
}
