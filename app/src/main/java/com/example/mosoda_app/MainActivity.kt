package com.example.mosoda_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
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
    }
    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            //TODO: fragment struktura
            //transaction.replace(R.id, fragment)
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
