package com.example.mosoda_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MyOrdersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_orders)

        val dao = Database.getInstance(this).dao
        val orders = findViewById<ListView>(R.id.orders)
        val orders_list = mutableListOf<String>()
        //globalis username
        val userName = MainActivity.UserName().USERNAME
        lifecycleScope.launch {
            val id = dao.getPeopleId(userName)
            val o = dao.getOrders(id)
            var info = ""
            o.forEach {
                info = it.pickUpDate + "\n" + it.deliveryDate + "\n"
                orders_list.add(info)
            }
            val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(this@MyOrdersActivity, android.R.layout.simple_list_item_1, orders_list)
            orders.adapter = arrayAdapter
        }
    }
}