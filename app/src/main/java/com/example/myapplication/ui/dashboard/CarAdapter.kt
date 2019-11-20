package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class CarAdapter(
    private var car: ArrayList<Car>,
    private val clickLambda: (Car) -> Unit
) : ListAdapter<Car, CarViewHolder>(Diff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder.create(parent, clickLambda)


    override fun getItemCount(): Int = car.size


    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(car[position])
    }

    override fun submitList(list: MutableList<Car>?) {
        super.submitList(list)
    }

    fun updateList(newList: ArrayList<Car>) {
        androidx.recyclerview.widget.DiffUtil.calculateDiff(DiffUtil(this.car, newList), false)
            .dispatchUpdatesTo(this)
        this.car.clear()
        this.car.addAll(newList)
    }

    override fun onBindViewHolder(
        holder: CarViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as? Bundle
            if (bundle != null) {
                holder.update(bundle)
            }
        }

    }
}