package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CarAdapter(
    private var car:List<Car>
): RecyclerView.Adapter<CarViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false)
        return CarViewHolder(view)
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(car[position])
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}