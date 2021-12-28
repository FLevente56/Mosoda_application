package com.example.mosoda_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class GuestHomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_home_page)

        val log_out = findViewById<Button>(R.id.log_out)
        val add_order = findViewById<Button>(R.id.add_order)

        log_out.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        add_order.setOnClickListener {

        }
    }
}