package com.example.myapplication.notes.info

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.notes.MainActivity
import com.example.myapplication.notes.OnFragmentListener
import com.example.myapplication.notes.constants.Constants
import com.example.myapplication.notes.dataBase.AppDatabase
import com.example.myapplication.notes.dataBase.entity.Notes
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.info_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NotesInfo : Fragment(), OnFragmentListener, CoroutineScope by MainScope() {

    private lateinit var mListener: OnFragmentListener
    private lateinit var db: AppDatabase
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as MainActivity)
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase(activity as MainActivity)
        checkPermissions()
        setTextOnET()
        initListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + "must implement interface")
        }
    }

    private fun setTextOnET() {
        if (arguments?.getString(Constants.DATA.ARG_ACTION) == Constants.ACTION.ADD_ACTION) btn_add.text = "ADD"
        else btn_add.text = "CHANGE"
        et_title.setText(arguments?.getString(Constants.DATA.ARG_TITLE).toString())
        et_description.setText(arguments?.getString(Constants.DATA.ARG_DESCRIPTION).toString())
    }

    private fun initListeners() {
        btn_add.setOnClickListener {
            if (arguments?.getString(Constants.DATA.ARG_ACTION).toString() == Constants.ACTION.ADD_ACTION) {
                addNotes(et_title.text.toString(), et_description.text.toString(), tv_latitude.text.toString(), tv_longitude.text.toString())
                mListener.changeFragment(Constants.ACTION.END_ADD_ACTION)
            } else {
                updateNotes(arguments?.getInt(Constants.DATA.ARG_ID) ?: 0, et_title.text.toString(),
                        et_description.text.toString(), tv_latitude.text.toString(), tv_longitude.text.toString())
                mListener.changeFragment(Constants.ACTION.END_ADD_ACTION)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1000 ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) location()
        }
    }

    private fun location() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            tv_latitude.text = location?.latitude.toString()
            tv_longitude.text = location?.longitude.toString()
        }
    }

    private fun checkPermissions() {
        val permissionsStatus = activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_COARSE_LOCATION) }
        if (permissionsStatus == PackageManager.PERMISSION_GRANTED) {
            location()
        } else {
            activity?.let { ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1000) }
        }
    }

    private fun updateNotes(id: Int, title: String, description: String, latitude: String, longitude: String) {
        launch { db.notesDao().updateNote(id, title, description, latitude, longitude) }
    }

    private fun addNotes(title: String, description: String, latitude: String, longitude: String) {
        launch { db.notesDao().save(Notes(0, title, description, latitude, longitude)) }
    }

    override fun changeFragment(comand: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun changeNotes(action: String, note: Notes) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(
                action: String = "",
                id: Int = 0,
                title: String = "",
                description: String = ""
        ): NotesInfo = NotesInfo().apply {
            arguments = Bundle().apply {
                putString(Constants.DATA.ARG_ACTION, action)
                putInt(Constants.DATA.ARG_ID, id)
                putString(Constants.DATA.ARG_TITLE, title)
                putString(Constants.DATA.ARG_DESCRIPTION, description)
            }
        }
    }
}
