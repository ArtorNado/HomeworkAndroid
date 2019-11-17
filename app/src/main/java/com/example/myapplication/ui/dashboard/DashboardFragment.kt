package com.example.myapplication.ui.dashboard

import android.app.AlertDialog
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
import kotlinx.android.synthetic.main.notice_dialog.view.*

class DashboardFragment : Fragment() {

    private val carList: ArrayList<Car> = CarRepository.getDataSource()

    private var adapter: CarAdapter? = null

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
            dialog()
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

    private fun dialog() {
        //Inflate the dialog with custom view
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.notice_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
            .setTitle("Login Form")
        //show dialog
        val mAlertDialog = mBuilder.show()
        //login button click of custom layout
        mDialogView.btn_add.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout
            val car = mDialogView.et_car.text.toString()
            val description = mDialogView.et_description.text.toString()

            var position = mDialogView.et_position.text.toString().toInt() - 1
            val listSize = carList.size
            if (position > listSize) {
                position = listSize
            }
            carList.add(position, Car(car, description))
            adapter?.updateList(carList)
            //set the input text in TextView
        }
        //cancel button click of custom layout
        mDialogView.btn_cancel.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()

        }
    }
}

