package com.example.mosoda_app

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val dao = Database.getInstance(this).dao
        val history_list = findViewById<ListView>(R.id.history_list)

        val pattern = "yyyy/MM/dd"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date: String = simpleDateFormat.format(Date())

        lifecycleScope.launch {
            val peopleAndCarpets = dao.getAllPeopleWithCarpets()
            val peopleWithOrders = dao.getPeopleWithOrders()
            val names = mutableListOf<String>()
            peopleAndCarpets.zip(peopleWithOrders).forEach { pair->
                var k = true
                pair.first.carpets.forEach { it2 ->
                    if(it2.done == "false") k = false
                }
                pair.second.orders.forEach {
                    if(it.deliveryDate > date) k = false
                }
                if(k) names.add(pair.first.people.name)

            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@HistoryActivity,
                android.R.layout.simple_list_item_1, names)
            history_list.adapter = arrayAdapter
        }
        history_list.setOnItemClickListener {adapterview, view, i, l ->
            val dialogBuilder = AlertDialog.Builder(this@HistoryActivity)
            lifecycleScope.launch {
                val people = dao.getAllPeople()
                val pwc = dao.getPeopleWithCarpets(people[i].id)
                val pwo = dao.getPeopleWithOrdersById(people[i].id)
                var inforamtions = "Pick-up date: " + pwo.orders[0].pickUpDate + "\nDelivery date: " +
                        pwo.orders[0].deliveryDate + "\n"
                var total_size = 0.0
                pwc[0].carpets.forEach {
                    total_size += it.size
                }
                inforamtions = inforamtions + "TOTAL SIZE: " + total_size +
                        "\nTOTAL PRICE: " + total_size * dao.getPrice()
                dialogBuilder.setMessage(inforamtions)
                val b = dialogBuilder.create()
                b.show()
            }
            dialogBuilder.setPositiveButton("Done",
                DialogInterface.OnClickListener { dialog, whichButton ->
                })
        }
    }
}