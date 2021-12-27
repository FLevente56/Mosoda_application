package com.example.mosoda_app

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mosoda_app.entities.Price
import kotlinx.coroutines.launch

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        var price: Float
        price = 7F

        val add_carpet_button = findViewById<Button>(R.id.add_carpet_button)
        val to_do_carpet = findViewById<Button>(R.id.to_do_carpet)
        val del_button = findViewById<Button>(R.id.delivery_button)
        val set_price = findViewById<Button>(R.id.set_price)
        val log_out = findViewById<Button>(R.id.log_out)
        val history = findViewById<Button>(R.id.history)

        add_carpet_button.setOnClickListener{
            val intent = Intent(this, AddCarpetActivity::class.java)
            startActivity(intent)
        }

        to_do_carpet.setOnClickListener{
            val intent = Intent(this, ToDoCarpetActivity::class.java)
            startActivity(intent)
        }

        del_button.setOnClickListener{
            val intent = Intent(this, DeliveryActivity::class.java)
            startActivity(intent)
        }

        set_price.setOnClickListener {
            lifecycleScope.launch {
                val dao = Database.getInstance(this@HomePage).dao
                //dao.insertPrice(Price(price))
                val dialogBuilder = AlertDialog.Builder(this@HomePage)
                val input = EditText(this@HomePage)
                dialogBuilder.setMessage("Set the price of the carpet\nThe current price: " +
                    dao.getPrice())
                input.setHint("Enter price")
                input.inputType = InputType.TYPE_CLASS_NUMBER
                dialogBuilder.setView(input)
                dialogBuilder.setPositiveButton("Done",
                    DialogInterface.OnClickListener { dialog, whichButton ->
                        price = input.text.toString().toFloat()
                        lifecycleScope.launch {
                            dao.updatePrice(price)
                        }
                    })
                dialogBuilder.setNegativeButton(
                    "Cancel",
                    DialogInterface.OnClickListener { dialog, which ->
                        dialog.cancel()
                    })
                val b = dialogBuilder.create()
                b.show()
            }
        }

        log_out.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        history.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}