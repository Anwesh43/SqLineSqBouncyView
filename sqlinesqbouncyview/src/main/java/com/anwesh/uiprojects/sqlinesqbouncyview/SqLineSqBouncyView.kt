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
val sizeFactor : Float = 2.9f
val strokeFactor : Float = 90f
val grayBack : Int = Color.parseColor("#9E9E9E")
val fillBack : Int = Color.parseColor("#3F51B5")
val foreColor : Int = Color.parseColor("#BDBDBD")

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()
