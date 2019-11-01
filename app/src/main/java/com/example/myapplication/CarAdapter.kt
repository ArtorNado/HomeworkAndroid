package com.example.myapplication

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(
    private var car: List<Car>,
    private val clickLambda: (String, Car) -> Unit
) : RecyclerView.Adapter<CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder.create(parent, clickLambda)


    override fun getItemCount(): Int = car.size


    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(car[position])
    }
}
