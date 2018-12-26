package com.hch.hooney.kotlinproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hch.hooney.kotlinproject.t1.MiniGameOneActivity
import com.hch.hooney.kotlinproject.t1.TextTypingActivity

class MainActivity : AppCompatActivity() {
    internal lateinit var exampleOne : Button
    internal lateinit var exampleTwo : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init() : Unit {
        exampleOne = findViewById(R.id.main_exam_one)
        exampleOne.setOnClickListener{
            startActivity(Intent(applicationContext, TextTypingActivity::class.java))
        }
        exampleTwo = findViewById(R.id.main_exam_two)
        exampleTwo.setOnClickListener{
            startActivity(Intent(applicationContext, MiniGameOneActivity::class.java))
        }
    }
}
