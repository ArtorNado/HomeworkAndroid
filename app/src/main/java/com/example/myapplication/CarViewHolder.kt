package com.example.myapplication

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template.*
import kotlinx.android.synthetic.main.template.view.*

class CarViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    val tv_car = containerView.tv_car
    val tv_description = containerView.tv_description
    val iv_image = containerView.iv_image

    fun bind(car: Car) {
        tv_description.text = car.description
        tv_car.text=car.car
    }
}