package com.example.mosoda_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        var add_carpet_button = findViewById<Button>(R.id.add_carpet_button)

        add_carpet_button.setOnClickListener{
            val intent = Intent(this, AddCarpetActivity::class.java)
            startActivity(intent)
        }
    }
}