package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: CarAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CarAdapter(CarRepository.getDataSource()) { Car ->
            navigateToDesc(Car)
        }
        rv_car.adapter = adapter;
    }


    private fun navigateToDesc(car: Car) {
        startActivity(SecondActivity.createIntent(this, car))
    }

}
