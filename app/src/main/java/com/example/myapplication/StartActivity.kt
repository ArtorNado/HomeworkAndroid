package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btn_all_music.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Fragment", "music_list")
            startActivity(intent)
        }
    }
}
