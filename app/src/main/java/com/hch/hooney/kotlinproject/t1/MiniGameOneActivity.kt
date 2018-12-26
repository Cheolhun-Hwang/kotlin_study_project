package com.hch.hooney.kotlinproject.t1

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.MenuItem
import android.widget.*
import com.hch.hooney.kotlinproject.R
import java.util.*

class MiniGameOneActivity : AppCompatActivity() {
    internal lateinit var rockBtn:ImageButton
    internal lateinit var scissorBtn : ImageButton
    internal lateinit var paperBtn : ImageButton

    internal var gameStarting : Boolean = false

    internal lateinit var androidPlayImage : ImageView
    internal lateinit var scoreTextview : TextView
    internal lateinit var goalTextView: TextView
    internal lateinit var roundTextView: TextView

    enum class playSelect{
        가위, 바위, 보
    }

    var NowScore : Int = 0
    var NowRound : Int = 0

    val goals : Array<Int> = arrayOf(5, 10, 15, 20, 25)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mini_game_one)

        var actionBar = supportActionBar
        // Set toolbar title/app title
        actionBar!!.title = "  Mini-GAME"

        // Set action bar/toolbar sub title
        actionBar.subtitle = "  안드로이드와 가위 바위 보"

        // Set action bar elevation
        actionBar.elevation = 4.0F

        // Display the app icon in action bar/toolbar
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.drawable.ic_android)
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)

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

    override fun onStart() {
        super.onStart()

        roundTextView.text = "${(NowRound+1)} /  ${goals.size} Round"
        goalTextView.text = "Goal : ${goals[NowRound]}"
        scoreTextview.text = "Score : ${NowScore}"
    }

    internal fun init():Unit{
        rockBtn = findViewById(R.id.example_two_btn_rock)
        paperBtn = findViewById(R.id.example_two_btn_papper)
        scissorBtn = findViewById(R.id.example_two_btn_sissor)

        scoreTextview = findViewById(R.id.example_two_score_text)
        goalTextView = findViewById(R.id.example_two_goal_text)
        roundTextView = findViewById(R.id.example_two_round_text)

        androidPlayImage = findViewById(R.id.example_two_show_android_play)
    }

    internal fun setEvent() : Unit{
        rockBtn.setOnClickListener{ view ->
            if(!gameStarting){
                gameStarting = true
                startGame(playSelect.바위)
            }

        }
        paperBtn.setOnClickListener { view ->
            if(!gameStarting){
                gameStarting = true
                startGame(playSelect.보)
            }
        }
        scissorBtn.setOnClickListener { view ->
            if(!gameStarting){
                gameStarting = true
                startGame(playSelect.가위)
            }
        }
    }

    internal fun getNowAndroidSelect() : playSelect {
        var num = (Math.random() * 10).toInt()
        Log.d("Rand", "Rand : ${num}")
        var result = playSelect.보
        when(num){
            0,2,8->  result =playSelect.가위
            1,6,7-> result = playSelect.바위
            else-> result =playSelect.보
        }
        return result
    }

    internal fun startGame(useSelect: playSelect) : Unit{
        var androidSelect : playSelect = getNowAndroidSelect()

        Log.d("Start Game", "Android : " + androidSelect)

        when(androidSelect){
            playSelect.보 -> {
                androidPlayImage.setImageDrawable(getDrawable(R.drawable.paper))

                if(useSelect == playSelect.가위){
                    //이김
                    userWin()
                }else if(useSelect == playSelect.바위){
                    //짐
                    userLost()
                }else{
                    //비김
                    userSame()
                }
            }
            playSelect.바위 ->{
                androidPlayImage.setImageDrawable(getDrawable(R.drawable.rock))

                if(useSelect == playSelect.보){
                    //이김
                    userWin()
                }else if(useSelect == playSelect.가위){
                    //짐
                    userLost()
                }else{
                    //비김
                    userSame()
                }
            }
            playSelect.가위->{
                androidPlayImage.setImageDrawable(getDrawable(R.drawable.sissor))

                if(useSelect == playSelect.바위){
                    //이김
                    userWin()
                }else if(useSelect == playSelect.보){
                    //짐
                    userLost()
                }else{
                    //비김
                    userSame()
                }
            }
        }
        gameStarting = false
    }

    internal fun userWin() :Unit{
        NowScore++
        scoreTextview.text = "Score : ${NowScore}"
        Toast.makeText(applicationContext, "와우! 당신이 이겼어요.", Toast.LENGTH_SHORT).show()
        checkGoal()
    }
    internal fun userLost() :Unit{
        NowScore--
        if(NowScore < 0){
            NowScore = 0
        }
        scoreTextview.text = "Score : ${NowScore}"
        Toast.makeText(applicationContext, "안드로이드가 이겼어요.", Toast.LENGTH_SHORT).show()
        checkGoal()
    }
    internal fun userSame() :Unit{
        Toast.makeText(applicationContext, "아쉽다. 비겼네요.", Toast.LENGTH_SHORT).show()
    }

    internal fun androidPlayInit():Unit{
        androidPlayImage.setImageDrawable(getDrawable(R.drawable.question))
    }

    internal fun failToAlert():Unit{
        var alert : AlertDialog.Builder = AlertDialog.Builder(applicationContext)
        alert.setTitle("Android Mini-Game")
        alert.setMessage("${(NowRound+1)} Round 목표 달성!! 다음 라운드로!")
        alert.setPositiveButton("다음으로"){ dialog, which ->

        }
    }

    internal fun checkGoal():Unit{
        Log.d("Check", "${goals[NowRound]} / ${NowScore}")
        if(goals[NowRound] <= NowScore){
            nextAlert()
        }
    }

    internal fun nextAlert():Unit{
        var alert : AlertDialog.Builder = AlertDialog.Builder(this)
        alert.setTitle("Android Mini-Game")
        alert.setMessage("${(NowRound+1)} Round 목표 달성!! 다음 라운드로!")
        alert.setPositiveButton("다음으로"){ dialog, which ->
            NowRound++
            showUpLevel()
        }
        alert.show()
    }

    internal fun showUpLevel():Unit{
        roundTextView.text = "${(NowRound+1)} / ${goals.size} Round"
        goalTextView.text = "Goal : ${goals[NowRound]}"
    }
}
