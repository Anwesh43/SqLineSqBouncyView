package com.anwesh.uiprojects.sqlinesqbouncyview

/**
 * Created by anweshmishra on 19/01/20.
 */


import android.view.View
import android.view.MotionEvent
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color
import android.content.Context
import android.app.Activity
import android.graphics.RectF

val nodes : Int = 5
val squares : Int = 2
val scGap : Float = 0.02f / (2 * squares - 1)
val sizeFactor : Float = 1.3f
val strokeFactor : Float = 90f
val grayBack : Int = Color.parseColor("#9E9E9E")
val fillBack : Int = Color.parseColor("#3F51B5")
val backColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawSqLine(i : Int, scale : Float, h : Float, size : Float, paint : Paint) {
    val sf : Float = scale.sinify().divideScale(i, squares)
    var lines : Int = 0
    val sc1 : Float = sf.divideScale(0, lines + 1)
    var sc2 : Float = 0f
    if (i != squares - 1) {
        lines = 1
        sc2 = sf.divideScale(1, lines + 1)
    }
    val hGap : Float = (h) / (squares + 2)
    val y : Float = h - hGap / 2 - hGap * i
    val yBar : Float = -size * sc1
    val yLine : Float = -hGap * sc2
    val yLF : Float = -size / 2
    save()
    translate(0f, y)
    paint.color = grayBack
    drawRect(RectF(-size / 2, -size / 2, size / 2, size / 2), paint)
    drawLine(0f, yLF, 0f, yLF - hGap * lines, paint)
    paint.color = fillBack
    drawRect(RectF(-size / 2, size / 2, size / 2, size / 2 + yBar), paint)
    drawLine(0f, yLF + yLine, 0f, yLF -hGap, paint)
    restore()
}

fun Canvas.drawSqLineSq(scale : Float, h : Float, size : Float, paint : Paint) {
    for (j in 0..(squares - 1)) {
        drawSqLine(j, scale, h, size, paint)
    }
}

fun Canvas.drawSLSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val size : Float = gap / sizeFactor
    paint.strokeCap = Paint.Cap.ROUND
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    save()
    translate(gap * (i + 1), 0f)
    drawSqLineSq(scale, h, size, paint)
    restore()
}

class SqLineSqBouncyView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += dir * scGap
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}