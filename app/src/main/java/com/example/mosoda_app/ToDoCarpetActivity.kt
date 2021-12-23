package com.example.mosoda_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class ToDoCarpetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_carpet)

        val dao = Database.getInstance(this).dao
        val carpet_list = findViewById<ListView>(R.id.carpet_list)
        val cods = mutableListOf<String>()
        lifecycleScope.launch{
            val orders = dao.getAllOrders()
            val carpets = dao.getAllCarpets()

            for(o in orders) {
                for(c in carpets) {
                    if(c.personId == o.id && o.deliveryDate == ""){
                        cods.add(c.cod)
                    }
                }
            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@ToDoCarpetActivity, android.R.layout.simple_list_item_1, cods)
            carpet_list.adapter = arrayAdapter
        }

        carpet_list.setOnItemClickListener {  adapterview, view, i, l ->
            val dialogBuilder = AlertDialog.Builder(this)
            val input = EditText(this)
            dialogBuilder.setMessage("Set the delivery date for carpet")
            input.setHint("Enter date")
            input.inputType = InputType.TYPE_CLASS_TEXT
            dialogBuilder.setView(input)
            dialogBuilder.setPositiveButton("Done",
                DialogInterface.OnClickListener { dialog, whichButton ->
                    val delivery_date = input.text.toString()
                    var id = -1
                    lifecycleScope.launch{
                        val carp = dao.getAllCarpets()
                        for (c in carp) {
                            if(c.cod == cods[i])  {
                                id = c.personId
                            }
                        }
                        dao.updateDeliveryDate(id, delivery_date)
                        Toast.makeText(this@ToDoCarpetActivity, "Delivery date added with success", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@ToDoCarpetActivity, HomePage::class.java)
                        startActivity(intent)
                    }
                })
            val b = dialogBuilder.create()
            dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel() })
            b.show()
        }

    }
}