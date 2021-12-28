package com.example.mosoda_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import com.example.mosoda_app.MainActivity.UserName.Companion.USERNAME
import kotlinx.coroutines.launch

class MyOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        val dao = Database.getInstance(this).dao
        val orders = findViewById<ListView>(R.id.orders)
        val orders_list = mutableListOf<String>()

        lifecycleScope.launch {
            val id = dao.getPeopleId(USERNAME)
            val o = dao.getOrders(id)
            val carps = dao.getCarpets(id)
            var total_price = 0.0
            carps.forEach { total_price += it.size * dao.getPrice()}
            var info = ""
            o.forEach {
                info = "Pick-up: " + it.pickUpDate + "\nDelivery: " + it.deliveryDate + "\nPrice: " +
                        total_price + "\n"
                orders_list.add(info)
            }

            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@MyOrdersActivity, android.R.layout.simple_list_item_1, orders_list)
            orders.adapter = arrayAdapter
        }
    }
}