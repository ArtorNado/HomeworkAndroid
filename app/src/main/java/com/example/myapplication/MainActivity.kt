package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: CarAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CarAdapter(getDataSource()) { car, _ ->
            navigateToDesc(car)
        }
        rv_car.adapter = adapter;
    }


    private fun navigateToDesc(car: String) {
        startActivity(SecondActivity.createIntent(this, car))
    }


    private fun getDataSource(): ArrayList<Car> = arrayListOf(
        Car(R.drawable.e63s, "Mercedes e63s amg", "612 horse power"),
        Car(R.drawable.rs6, "Audi RS6 performance ", "605 horse power"),
        Car(R.drawable.chiron, "Bugatti Chiron", "1500 horse power"),
        Car(R.drawable.p1, "Maclaren P1", "920 horse power"),
        Car(R.drawable.f12, "Ferrari F12 Berlinetta", "740 horse power")
    )
}
