package com.example.myapplication.ui.dashboard

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil

object Diff : DiffUtil.ItemCallback<Car>() {

    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem.car == newItem.car

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean =
            oldItem.description == newItem.description

    override fun getChangePayload(oldItem: Car, newItem: Car): Any? {
        val diffBundle = Bundle()
        if (oldItem.car != newItem.car) {
            diffBundle.putString("car", newItem.car)
        }
        if (oldItem.description != newItem.description) {
            diffBundle.putString("description", newItem.description)
        }
        return if (diffBundle.isEmpty) null else diffBundle
    }

}
