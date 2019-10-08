package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_authorization.*

class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        et_sign_in_pass.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                ti_sign_in_pass.error = null
                ti_sign_in_pass.isErrorEnabled = false
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        btn_logIn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            if(et_sign_in_pass.text.toString() == PasswordRepository.password)
                startActivity(intent)
            else
                setPasswordError()
        }

        btn_changePassword.setOnClickListener{
            val intent = Intent(this, ChangePasActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setPasswordError() {
        ti_sign_in_pass.error = getString(R.string.validate_password)
    }
}
