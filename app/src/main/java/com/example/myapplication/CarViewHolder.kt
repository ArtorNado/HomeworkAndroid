package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template.view.*

class CarViewHolder(
    override val containerView: View,
    private val clickLambda: (String, Car) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    val tv_car = containerView.tv_car
    val tv_description = containerView.tv_description
    val iv_image = containerView.iv_image

    fun bind(car: Car) {
        tv_description.text = car.description
        tv_car.text = car.car
        iv_image.setImageResource(car.img)
        itemView.setOnClickListener {
            clickLambda(car.car, car)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (String, Car) -> Unit) =
            CarViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false),
                clickLambda
            )
    }
}
