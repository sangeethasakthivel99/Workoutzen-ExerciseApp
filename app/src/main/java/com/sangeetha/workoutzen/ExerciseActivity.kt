package com.sangeetha.workoutzen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_exercise.*

class ExerciseActivity : AppCompatActivity() {

    private var restTimer: CountDownTimer? = null

    private var restProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setupToolBar()
        setUpRestView()
        initListener()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }
    }

    private fun setupToolBar() {
        setSupportActionBar(toolbar)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpRestView() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setRestProgressBar() {
        progressbar.progress = restProgress
        restTimer = object :CountDownTimer(10_000, 1000) {
            override fun onTick(p0: Long) {
                restProgress ++
                progressbar.progress = REST_TIME - restProgress
                tvTimer.text = (REST_TIME - restProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity,
                    "We Will Start Exercising Now", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

    private fun initListener() {
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}