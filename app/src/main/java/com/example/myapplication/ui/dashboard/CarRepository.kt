package com.example.myapplication.ui.dashboard

object CarRepository {

    fun getDataSource(): ArrayList<Car> = arrayListOf(
        Car("Mercedes e63s amg", "612 horse power"),
        Car("Audi RS6 performance ", "605 horse power"),
        Car("Bugatti Chiron", "1500 horse power"),
        Car("Maclaren P1", "920 horse power"),
        Car("Ferrari F12 Berlinetta", "740 horse power"),
        Car("Bugatti Chiron", "1500 horse power"),
        Car("Maclaren P1", "920 horse power"),
        Car("Ferrari F12 Berlinetta", "740 horse power")
    )
}