package com.example.mosoda_app

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.mosoda_app.MainActivity.UserName.Companion.USERNAME
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    class UserName() : Application() {
        companion object{
            var USERNAME = ""
        }
        override fun onCreate() {
            super.onCreate()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val button_login = findViewById<Button>(R.id.attempt_login_button)
        val button_sign_up = findViewById<Button>(R.id.to_registrate_button)

        //adatbazis
        val dao = Database.getInstance(this).dao


        button_login.setOnClickListener{
            val userName = username.text.toString()
            val passWord = password.text.toString()
            //itt ellenorozni hogy helyes-e a felhasznalo
            lifecycleScope.launch{
                val profil = dao.getProfile(userName, passWord)
                if(profil.isEmpty()) {
                    //de kell irni a hibauzenetet, hogy nem sikerult a bejelentezes
                    Toast.makeText(this@MainActivity, "Wrong username or password", Toast.LENGTH_SHORT).show()
                }
                else{
                    if(profil[0].rank == "admin"){
                        val intent = Intent(this@MainActivity, HomePage::class.java)
                        startActivity(intent)
                    }
                    else{
                        //enegdjen be vendeg modba
                        USERNAME = userName
                        val intent = Intent(this@MainActivity, GuestHomePage::class.java)
                        startActivity(intent)
                    }

                }
            }

        }

        button_sign_up.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
    
}
