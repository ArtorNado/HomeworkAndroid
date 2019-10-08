package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_change_pas.*

class ChangePasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pas)
    }

    fun onButtonClick(view: View){
        if(!et_change_password.text.toString().isEmpty())
            PasswordRepository.password = et_change_password.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
