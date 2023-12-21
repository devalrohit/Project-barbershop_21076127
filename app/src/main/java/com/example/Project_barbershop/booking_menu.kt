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

class booking_menu : AppCompatActivity() {
    private lateinit var HAIRCUT: Button
    private lateinit var SMOOTHING: Button
    private lateinit var PERM: Button
    private lateinit var COLORING: Button
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_menu)

        HAIRCUT = findViewById(R.id.HAIRCUT)
        SMOOTHING = findViewById(R.id.SMOOTHING)
        PERM = findViewById(R.id.PERM)
        COLORING = findViewById(R.id.COLORING)
        requestQueue = Volley.newRequestQueue(this)

        HAIRCUT.setOnClickListener {
            val menu = HAIRCUT.text.toString() // Menggunakan teks pada tombol sebagai menu
            insertDataIntoDatabase("HAIRCUT", menu)
        }

        SMOOTHING.setOnClickListener {
            val menu = SMOOTHING.text.toString() // Menggunakan teks pada tombol sebagai menu
            insertDataIntoDatabase("SMOOTHING", menu)
        }

        PERM.setOnClickListener {
            val menu = PERM.text.toString() // Menggunakan teks pada tombol sebagai menu
            insertDataIntoDatabase("PERM", menu)
        }

        COLORING.setOnClickListener {
            val menu = COLORING.text.toString() // Menggunakan teks pada tombol sebagai menu
            insertDataIntoDatabase("COLORING", menu)
        }
    }

    private fun insertDataIntoDatabase(serviceType: String, menu: String) {
        val url =
            Db_Contract.urlBokingmenu // Gunakan URL endpoint yang sudah Anda tetapkan sebelumnya

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Toast.makeText(
                    this,
                    "Data untuk $serviceType dengan menu $menu dimasukkan ke dalam database",
                    Toast.LENGTH_SHORT
                ).show()
                moveToNextActivity() // Panggil fungsi moveToNextActivity setelah proses selesai
            },
            { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_SHORT).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["service_type"] = serviceType
                params["menu"] = menu // Menu ditambahkan ke dalam params
                return params
            }
        }

        val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
        requestQueue.add(stringRequest)
    }

    private fun moveToNextActivity() {
        val intent = Intent(
            this,
            booking_menu_capster::class.java
        ) // Ganti NextActivity dengan aktivitas yang sesuai
        startActivity(intent)
    }
}
