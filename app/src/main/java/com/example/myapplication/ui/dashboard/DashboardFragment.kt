package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    private val carList: ArrayList<Car> = CarRepository.getDataSource()

    private lateinit var adapter: CarAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_car.layoutManager = LinearLayoutManager(context)
        adapter = CarAdapter(CarRepository.getDataSource()) { Car ->
            deleteItem(Car)
        }
        btn_update.setOnClickListener {
            context?.let { it1 -> DialogWindow.newInstance().dialog(carList, it1, adapter) }
        }
        rv_car?.adapter = adapter
        setRecyclerViewItemTouchListener()

    }

    private fun deleteItem(car: Car) {
        carList.remove(car)
        adapter?.updateList(carList)
    }

    private fun setRecyclerViewItemTouchListener() {
        val itemTouchCallback = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                carList.removeAt(position)
                adapter?.updateList(carList)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        rv_car.addItemDecoration(itemTouchHelper)
        itemTouchHelper.attachToRecyclerView(rv_car)
    }

    companion object {
        fun newInstance(): DashboardFragment = DashboardFragment()
    }

}
