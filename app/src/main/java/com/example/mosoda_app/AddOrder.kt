package com.example.mosoda_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mosoda_app.MainActivity.UserName.Companion.USERNAME
import com.example.mosoda_app.entities.Carpets
import com.example.mosoda_app.entities.Orders
import kotlinx.coroutines.launch

class AddOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        val dao = Database.getInstance(this).dao
        val allCarpets: MutableList<EditText> = arrayListOf()
        val allCarpetsSize: MutableList<EditText> = arrayListOf()
        val add_carpet_btn = findViewById<Button>(R.id.add_button)
        val done_btn = findViewById<Button>(R.id.done_button)

        add_carpet_btn.setOnClickListener {
            val editLinearLayout = findViewById<LinearLayout>(R.id.linearLayout)
            val dynamicTextview = EditText(this)
            val dynamicTextview2 = EditText(this)

            dynamicTextview.hint = "Carpet"
            dynamicTextview2.hint = "Size"

            allCarpets.add(dynamicTextview)
            allCarpetsSize.add(dynamicTextview2)
            // add TextView to LinearLayout
            editLinearLayout.addView(dynamicTextview)
            editLinearLayout.addView(dynamicTextview2)
        }

        done_btn.setOnClickListener {
            val carpet = findViewById<EditText>(R.id.Carpet).text.toString()
            val size = findViewById<EditText>(R.id.carpet_size).text.toString()

            if(carpet.isEmpty() || size.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                lifecycleScope.launch {
                    val id = dao.getPeopleId(USERNAME)
                    val newCarpet = Carpets(carpet, size.toFloat(), "false", id)
                    dao.insertCarpet(newCarpet)
                    val carps: List<Carpets> = arrayListOf(newCarpet)

                    allCarpets.zip(allCarpetsSize).forEach{ pair ->
                        val newCarpet2 = Carpets(pair.component1().text.toString(),
                            pair.component2().text.toString().toFloat(),"false", id)
                        dao.insertCarpet(newCarpet2)
                        carps.toMutableList().add(newCarpet2)
                    }
                    val newOrder = Orders(id, "", "", "")
                    dao.insertOrder(newOrder)

                    Toast.makeText(this@AddOrder, "Added with success", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@AddOrder, GuestHomePage::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}