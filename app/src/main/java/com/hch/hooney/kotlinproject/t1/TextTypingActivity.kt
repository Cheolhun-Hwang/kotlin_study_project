package com.hch.hooney.kotlinproject.t1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.hch.hooney.kotlinproject.R

class TextTypingActivity : AppCompatActivity() {
    val TAG : String = "TextTyping"
    internal lateinit var sendBTN : Button
    internal lateinit var showTextView : TextView
    internal lateinit var edit : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_typing)

        var actionbar = supportActionBar
        actionbar!!.title = "  Chat_Example"

        // Set action bar/toolbar sub title
        actionbar.subtitle = "  간단한 이벤트 연습하기"

        // Set action bar elevation
        actionbar.elevation = 4.0F
        actionbar.setDisplayHomeAsUpEnabled(true)

        init()
        setEvent()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
//        return super.onSupportNavigateUp()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun init() : Unit{
        sendBTN = findViewById(R.id.example_one_send_btn)
        showTextView = findViewById(R.id.example_one_show_text)
        edit = findViewById(R.id.example_one_edittext)
    }

    fun setEvent() : Unit{
        sendBTN.setOnClickListener(View.OnClickListener {
            textSendMethod()
        })
        edit.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if((event.action == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER) ){
                textSendMethod()
                true
            }else{
                false
            }
        })
    }

    fun textSendMethod() : Unit{
        var tempText : String?= null
        tempText = edit.text.toString()
        if(tempText.isNullOrEmpty() || tempText.isBlank()){
            Toast.makeText(applicationContext, "1자 이상 작성해주세요.", Toast.LENGTH_SHORT).show()
        }else{
            showTextView.text = tempText
            edit.text.clear()
        }
    }
}
