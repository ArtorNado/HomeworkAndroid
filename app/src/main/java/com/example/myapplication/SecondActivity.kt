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
        val description = intent?.extras?.getString(KEY_DESCRIPTION) ?: "DEFAULT NAME"
        val img = intent?.extras?.getInt(KEY_IMG) ?: "DEFAULT NAME"
        tv_car.text = car
        tv_description.text = description
        iv_image.setImageResource(img as Int)
    }

    companion object {
        private const val KEY_CAR = "car"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_IMG = "img"

        fun createIntent(activity: Activity, car: Car) =
            Intent(activity, SecondActivity::class.java).apply {
                putExtra(KEY_CAR, car.car)
                putExtra(KEY_DESCRIPTION, car.description)
                putExtra(KEY_IMG, car.img)
            }
    }
}
