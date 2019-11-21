package com.example.myapplication.ui.dashboard

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.R
import kotlinx.android.synthetic.main.notice_dialog.view.*

class DialogWindow {

    fun dialog(carList: ArrayList<Car>, context: Context, adapter: CarAdapter) {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.notice_dialog, null)
        val mBuilder = AlertDialog.Builder(context).apply {
            setView(mDialogView)
            setTitle("Add car")
        }
        val mAlertDialog = mBuilder.show()
        mDialogView.btn_add.setOnClickListener {
            mAlertDialog.dismiss()
            val car = mDialogView.et_car.text.toString()
            val description = mDialogView.et_description.text.toString()
            var position = mDialogView.et_position.text.toString().toInt() - 1
            val listSize = carList.size
            if (position > listSize) {
                position = listSize
            }
            carList.add(position, Car(car, description, R.drawable.default_car))
            adapter.updateList(carList)
        }
        mDialogView.btn_cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    companion object {
        fun newInstance(): DialogWindow = DialogWindow()
    }

}
