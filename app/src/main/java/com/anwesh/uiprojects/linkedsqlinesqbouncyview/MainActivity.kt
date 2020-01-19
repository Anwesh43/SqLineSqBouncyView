package com.anwesh.uiprojects.linkedsqlinesqbouncyview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.sqlinesqbouncyview.SqLineSqBouncyView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SqLineSqBouncyView.create(this)
    }
}
