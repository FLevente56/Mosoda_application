package com.example.mosoda_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mosoda_app.entities.People
import kotlinx.coroutines.launch

class RegistrationWithDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_with_details)

        val dao = Database.getInstance(this).dao
        val done_btn = findViewById<Button>(R.id.done)

        done_btn.setOnClickListener {
            val name = findViewById<EditText>(R.id.person_name).text.toString()
            val address = findViewById<EditText>(R.id.address).text.toString()
            val phone = findViewById<EditText>(R.id.phone).text.toString()

            if(name.isEmpty() || address.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                lifecycleScope.launch {
                    val newPeople = People(dao.getAllPeople().size + 1, name, address, phone)
                    dao.insertPeople(newPeople)
                    val intent = Intent(this@RegistrationWithDetails, GuestHomePage::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}