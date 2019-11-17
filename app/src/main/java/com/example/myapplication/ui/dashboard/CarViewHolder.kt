package com.example.myapplication.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.template.view.*

class CarViewHolder(
    override val containerView: View,
    private val clickLambda: (Car) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    val tv_car = containerView.tv_car
    val tv_description = containerView.tv_description
    val btn_delete = containerView.btn_delete

    fun bind(car: Car) {
        tv_description.text = car.description
        tv_car.text = car.car
        btn_delete.setOnClickListener {
            clickLambda(car)
        }
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Car) -> Unit) =
            CarViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false),
                clickLambda
            )
    }
}