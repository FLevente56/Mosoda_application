package com.example.mosoda_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        var button_login = findViewById<Button>(R.id.attempt_login_button)
        var button_sign_up = findViewById<Button>(R.id.to_registrate_button)

        //adatbazis
        val dao = Database.getInstance(this).dao
        val bottnav: BottomNavigationView = findViewById(R.id.bottom_navigation_view_login);
        bottnav.setOnItemSelectedListener() {
            when(it.itemId) {
                //TODO: fragmentek megval
                R.id.back_login_button -> { // Ez nem tudom meg mit csinaljon valszeg lepjen ki az App-bol
                    //replaceFragment()
                    return@setOnItemSelectedListener true
                }
                //TODO: fragmentek megval
                R.id.attempt_login_button -> { /*TODO: attempt_login()-fuggv ez vissza kell teritsen egy True-t h sikerult-e a belepes
                                                 ha igen akkor pedig atkuld a rendes kezdokepernyore*/
                    val sikeres :Boolean=true;
                    // sikeres=attempt_login(); //-vlm ilyesmi
                    if(sikeres)
                    {
                        //replaceFragment()
                        return@setOnItemSelectedListener true
                    }
                }
                //TODO: fragmentek megval
                R.id.to_registrate_button -> { /*TODO : atkuld a registration_layoutra es ott hasonloan fog viselkedni registrate()-fugv felvesz az adatbazisba
                                                 ha sikeres akkor pedig visszakuld erre a kepernyore h jelentkezz be */
                    //replaceFragment()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

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
                    //engedjen be az alkalmazasba
                    val intent = Intent(this@MainActivity, HomePage::class.java)
                    startActivity(intent)
                }
            }

        }

        button_sign_up.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            //TODO: fragment struktura
            //transaction.replace(R.id., fragment)
            transaction.commit()
        }
    }
    private fun replaceChild(fragment: Fragment)
    {
        if (fragment != null) {
            val childTransaction = supportFragmentManager.beginTransaction()
            //TODO: fragment struktura -ez nem 100% fog kelleni, meglatom meg
            //childTransaction.replace(R.id., fragment)
            childTransaction.commit()
        }
    }
    
}
