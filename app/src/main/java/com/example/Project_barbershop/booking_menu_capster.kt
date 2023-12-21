package com.example.Project_barbershop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class booking_menu_capster : AppCompatActivity() {
    private lateinit var andreButton: Button
    private lateinit var revanButton: Button
    private lateinit var rohitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_menu_capster)

        andreButton = findViewById(R.id.andre)
        revanButton = findViewById(R.id.revan)
        rohitButton = findViewById(R.id.rohit)

        andreButton.setOnClickListener {
            val menu = andreButton.text.toString()
            insertDataIntoDatabase(menu)
        }

        revanButton.setOnClickListener {
            val menu = revanButton.text.toString()
            insertDataIntoDatabase(menu)
        }

        rohitButton.setOnClickListener {
            val menu = rohitButton.text.toString()
            insertDataIntoDatabase(menu)
        }
    }

    private fun insertDataIntoDatabase(menu: String) {
        val url = Db_Contract.urlBokingmenuCapster

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Toast.makeText(
                    this,
                    "Data untuk $menu dimasukkan ke dalam database",
                    Toast.LENGTH_SHORT
                ).show()
                moveToNextActivity()
            },
            { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["capster"] = menu
                return params
            }
        }

        val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(stringRequest)
    }

    private fun moveToNextActivity() {
        val intent = Intent(
            this,
            tanggal::class.java
        )
        startActivity(intent)
    }
}
