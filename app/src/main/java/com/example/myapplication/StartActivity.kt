package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.constants.Constants
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        btn_all_music.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Fragment", Constants.FILTER.MUSIC_LIST)
            intent.putExtra("author", Constants.FILTER.ALL_AUTHORS)
            startActivity(intent)
        }

        btn_author_list.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Fragment", Constants.FILTER.AUTHOR_LIST)
            startActivity(intent)
        }
    }
}
