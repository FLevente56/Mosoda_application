package com.example.mosoda_app

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.mosoda_app.entities.People
import kotlinx.coroutines.launch
import android.widget.EditText
import com.example.mosoda_app.entities.Carpets
import com.example.mosoda_app.entities.Orders


class AddCarpetActivity : AppCompatActivity() {
    val allCarpets: MutableList<EditText> = arrayListOf()
    val allCarpetsSize: MutableList<EditText> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_carpet)

        val dao = Database.getInstance(this).dao

        val done_btn = findViewById<Button>(R.id.done_button)
        val add_carpet_btn = findViewById<Button>(R.id.add_button)

        done_btn.setOnClickListener{
            val name = findViewById<EditText>(R.id.name).text.toString()
            val address = findViewById<EditText>(R.id.address).text.toString()
            val phone = findViewById<EditText>(R.id.phone).text.toString()
            val pick_up_date = findViewById<EditText>(R.id.pick_up_date).text.toString()
            val carpet = findViewById<EditText>(R.id.Carpet).text.toString()
            val size = findViewById<EditText>(R.id.carpet_size).text.toString()
            val comment = findViewById<EditText>(R.id.comment).text.toString()

            if(name.isEmpty() || address.isEmpty() || phone.isEmpty() ||
                pick_up_date.isEmpty() || carpet.isEmpty() || size.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                lifecycleScope.launch{
                    val id = dao.getAllPeople().size + 1
                    val newPeople = People(id, name, address, phone)

                    dao.insertPeople(newPeople)
                    val newCarpet = Carpets(carpet, size.toFloat(), "false", id)
                    dao.insertCarpet(newCarpet)
                    val carps: List<Carpets> = arrayListOf(newCarpet)

                    allCarpets.zip(allCarpetsSize).forEach{ pair ->
                        val newCarpet2 = Carpets(pair.component1().text.toString(),
                            pair.component2().text.toString().toFloat(),"false", id)
                        dao.insertCarpet(newCarpet2)
                        carps.toMutableList().add(newCarpet2)
                    }
                    val newOrder = Orders(id, pick_up_date, "", comment)
                    dao.insertOrder(newOrder)

                    Toast.makeText(this@AddCarpetActivity, "Added with success", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this@AddCarpetActivity, HomePage::class.java)
                    startActivity(intent)
                }

            }

        }

        add_carpet_btn.setOnClickListener{
            val editLinearLayout = findViewById<LinearLayout>(R.id.linearLayout)
            val dynamicTextview = EditText(this)
            val dynamicTextview2 = EditText(this)

            dynamicTextview.hint = "Carpet"
            dynamicTextview2.hint = "Size"
            dynamicTextview.setBackgroundColor(Color.parseColor("#AED6F1"))
            dynamicTextview2.setBackgroundColor(Color.parseColor("#AED6F1"))
            dynamicTextview.setHintTextColor(Color.parseColor("#283747"))
            dynamicTextview2.setHintTextColor(Color.parseColor("#283747"))
            dynamicTextview.setTextColor(Color.parseColor("#283747"))
            dynamicTextview2.setTextColor(Color.parseColor("#283747"))


            allCarpets.add(dynamicTextview)
            allCarpetsSize.add(dynamicTextview2)
            // add TextView to LinearLayout
            editLinearLayout.addView(dynamicTextview)
            editLinearLayout.addView(dynamicTextview2)
        }
    }
}