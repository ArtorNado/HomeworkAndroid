package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.template.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val car = intent?.extras?.getString(KEY_CAR) ?: "DEFAULT NAME"
        tv_car.text = car
    }

    companion object {
        private const val KEY_CAR = "car"

        fun createIntent(activity: Activity, car: String) =
            Intent(activity, SecondActivity::class.java).apply {
                putExtra(KEY_CAR, car)
            }
    }
}
