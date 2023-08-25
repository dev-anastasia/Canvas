package com.example.baloons

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.TypedValue
import java.util.*

class Circle(private val context: Context, val canvas: Canvas) {

    private val random = Random()
    var x = random.nextInt().toFloat()
    var y = random.nextInt().toFloat()
    //var randomW = random.nextInt(width).toFloat()
    //var randomH = random.nextInt(height).toFloat()
    val radius = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, 10F, context.resources.displayMetrics
    )

    public fun draw (canvas: Canvas, paint: Paint) {
        canvas.drawCircle(x, y, radius, paint)
    }

    public fun update() {
        x += 1
        y += 1
    }
}