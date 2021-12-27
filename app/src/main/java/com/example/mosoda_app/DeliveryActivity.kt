package com.example.mosoda_app

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DeliveryActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        val dao = Database.getInstance(this).dao
        val name_list = findViewById<ListView>(R.id.name_list)

        lifecycleScope.launch {
            val peopleAndCarpets = dao.getAllPeopleWithCarpets()
            val names = mutableListOf<String>()
            peopleAndCarpets.forEach {
                var k = true
                it.carpets.forEach { it2 ->
                    if(it2.done == "false") k = false
                }
                if(k) names.add(it.people.name)

            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@DeliveryActivity,
                android.R.layout.simple_list_item_1, names)
            name_list.adapter = arrayAdapter
        }
        name_list.setOnItemClickListener {adapterview, view, i, l ->
            val dialogBuilder = AlertDialog.Builder(this@DeliveryActivity)
            lifecycleScope.launch {
                val people = dao.getAllPeople()
                val pwc = dao.getPeopleWithCarpets(people[i].id)
                var inforamtions = ""
                var total_size = 0.0
                pwc[0].carpets.forEach { inforamtions = inforamtions + "Cod: " +
                        it.cod + "\nSize: " + it.size + "\n"
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