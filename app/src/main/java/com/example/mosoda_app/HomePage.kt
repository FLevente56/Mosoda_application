package com.example.mosoda_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val add_carpet_button = findViewById<Button>(R.id.add_carpet_button)
        val to_do_carpet = findViewById<Button>(R.id.to_do_carpet)

        add_carpet_button.setOnClickListener{
            val intent = Intent(this, AddCarpetActivity::class.java)
            startActivity(intent)
        }

        to_do_carpet.setOnClickListener{
            val intent = Intent(this, ToDoCarpetActivity::class.java)
            startActivity(intent)
        }
    }
}