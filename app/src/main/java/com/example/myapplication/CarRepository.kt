package com.example.myapplication

class CarRepository {

    fun getDataSource(): ArrayList<Car> = arrayListOf(
        Car(R.drawable.e63s, "Mercedes e63s amg", "612 horse power"),
        Car(R.drawable.rs6, "Audi RS6 performance ", "605 horse power"),
        Car(R.drawable.chiron, "Bugatti Chiron", "1500 horse power"),
        Car(R.drawable.p1, "Maclaren P1", "920 horse power"),
        Car(R.drawable.f12, "Ferrari F12 Berlinetta", "740 horse power")
    )
}
