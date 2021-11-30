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
        val bottnav: BottomNavigationView = findViewById(R.id.bottom_navigation_view_login);
        bottnav.setOnItemSelectedListener() {
            when(it.itemId) {
                //TODO: fragmentek megval
                R.id.back_login_button -> {
                    replaceFragment()
                    return@setOnItemSelectedListener true
                }
                //TODO: fragmentek megval
                R.id.attempt_login_button -> {
                    replaceFragment()
                    return@setOnItemSelectedListener true
                }
                //TODO: fragmentek megval
                R.id.to_registrate_button -> {
                    replaceFragment()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id., fragment)
            transaction.commit()
        }
    }
    private fun replaceChild(fragment: Fragment)
    {
        if (fragment != null) {
            val childTransaction = supportFragmentManager.beginTransaction()
            childTransaction.replace(R.id., fragment)
            childTransaction.commit()
        }
    }
    
}
