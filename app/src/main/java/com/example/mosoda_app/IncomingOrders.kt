package com.example.mosoda_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class IncomingOrders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_orders)

        val dao = Database.getInstance(this).dao
        val incomingOrdersList = findViewById<ListView>(R.id.incoming_orders_list)
        val list = mutableListOf<String>()
        val listOfIds = mutableListOf<Int>()

        lifecycleScope.launch {
            val allOrders = dao.getAllOrders()
            allOrders.forEach {
                if(it.pickUpDate == "") {
                    listOfIds.add(it.id)
                    val data = dao.getPeopleById(it.id)
                    list.add("Name: ${data.name}\nAddress: ${data.address}\nPhone: ${data.teNum}\n")
                }
            }
            if(list.isEmpty()){
                list.add("You don't have orders yet")
            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@IncomingOrders, android.R.layout.simple_list_item_1, list)
            incomingOrdersList.adapter = arrayAdapter
        }

        incomingOrdersList.setOnItemClickListener { adapterview, view, i, l ->
            val dialogBuilder = AlertDialog.Builder(this)
            val pattern = "yyyy/MM/dd"
            val simpleDateFormat = SimpleDateFormat(pattern)
            val todayDate: String = simpleDateFormat.format(Date())
            dialogBuilder.setMessage("Are you picked up this order?")

            dialogBuilder.setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, whichButton ->
                    lifecycleScope.launch{
                        dao.updatePickUpDate(listOfIds[i], todayDate)
                        Toast.makeText(this@IncomingOrders, "You picked up the order with success", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@IncomingOrders, HomePage::class.java)
                        startActivity(intent)
                    }
                })
            dialogBuilder.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel() })
            val b = dialogBuilder.create()
            b.show()
        }
    }
}