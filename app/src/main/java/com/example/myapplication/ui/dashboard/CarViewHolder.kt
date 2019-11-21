package com.example.myapplication.ui.dashboard

import android.os.Bundle
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

    private val tv_car = containerView.tv_car
    private val tv_description = containerView.tv_description
    private val btn_delete = containerView.btn_delete
    private val iv_image = containerView.iv_image

    fun bind(car: Car) {
        tv_description.text = car.description
        tv_car.text = car.car
        containerView.setBackgroundResource(car.image)
        btn_delete.setOnClickListener {
            clickLambda(car)
        }
    }

    fun update(bundle: Bundle) {
        tv_car.text = bundle.getString("car") ?: "NULL"
        tv_description.text = bundle.getString("description") ?: "NULL"
    }

    companion object {

        fun create(parent: ViewGroup, clickLambda: (Car) -> Unit) =
                CarViewHolder(
                        LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false),
                        clickLambda
                )
    }
}
