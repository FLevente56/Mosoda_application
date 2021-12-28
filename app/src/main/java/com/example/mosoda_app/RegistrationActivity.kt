package com.example.mosoda_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mosoda_app.entities.Employees
import com.example.mosoda_app.entities.Profils
import kotlinx.coroutines.launch

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)

        var userName = findViewById<EditText>(R.id.username)
        var password1 = findViewById<EditText>(R.id.reg_password1)
        var password2 = findViewById<EditText>(R.id.reg_password2)
        var reg_button = findViewById<Button>(R.id.to_registrate_button)

        val dao = Database.getInstance(this).dao

        reg_button.setOnClickListener {
            val passw1 = password1.text.toString()
            val passw2 = password2.text.toString()
            val username = userName.text.toString()
            if(username.isEmpty() || passw1.isEmpty() || passw2.isEmpty()) {
                Toast.makeText(this, "Field must be fill!", Toast.LENGTH_SHORT).show()
            }
            else {
                if (passw1 != passw2) {
                    Toast.makeText(this, "The two passwords do not match", Toast.LENGTH_SHORT)
                        .show()
                } else {

                    lifecycleScope.launch {
                        val count = dao.getAllProfils().size + 1
                        val newProfil = Profils(id = count, userName = username,
                            password = passw1, "client")

                        dao.insertProfile(newProfil)
                        val newEmployee = Employees(count, count.toString(),"client")
                        dao.insertEmployee(newEmployee)
                        //adatok kitoltese
                        val intent = Intent(this@RegistrationActivity, RegistrationWithDetails::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}