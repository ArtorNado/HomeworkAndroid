package com.example.myapplication.notes.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class Permissions {

    private fun checkPermissions(context: Context) {
        val permissionsStatus = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionsStatus == PackageManager.PERMISSION_GRANTED) {
            Log.e("PERMISSION", "GRANTED")
        } else {
            ActivityCompat.requestPermissions(context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1000)
        }
    }

}
