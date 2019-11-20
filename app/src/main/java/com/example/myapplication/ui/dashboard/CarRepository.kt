package com.example.myapplication.ui.dashboard

import com.example.myapplication.R

object CarRepository {

    fun getDataSource(): ArrayList<Car> = arrayListOf(
            Car("Mercedes e63s amg", "612 horse power", R.drawable.e63ss),
            Car("Audi RS6 performance ", "605 horse power", R.drawable.rs6),
            Car("Bugatti Chiron", "1500 horse power", R.drawable.chiron),
            Car("Maclaren P1", "920 horse power", R.drawable.p1),
            Car("Ferrari F12 Berlinetta", "740 horse power", R.drawable.f12)
    )
}