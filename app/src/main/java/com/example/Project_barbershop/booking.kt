package com.example.Project_barbershop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class booking : AppCompatActivity() {
    private var bookingnama: EditText? = null
    private var bokingnomorhp: EditText? = null
    private var btnBoking: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        bookingnama = findViewById(R.id.bookingnama)
        bokingnomorhp = findViewById(R.id.bokingnomorhp)
        btnBoking = findViewById(R.id.bottonbokingnamadanno)

        btnBoking?.setOnClickListener {
            val nama = bookingnama?.text.toString()
            val nomor_hp = bokingnomorhp?.text.toString()

            if (!(nama.isEmpty() || nomor_hp.isEmpty())) {
                val stringRequest = object : StringRequest(
                    Request.Method.POST,
                    Db_Contract.urlBokingdata,
                    { response ->
                        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, booking_menu::class.java))
                    },
                    { error ->
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["nama"] = nama
                        params["nomor_hp"] = nomor_hp
                        return params
                    }
                }

                val requestQueue: RequestQueue = Volley.newRequestQueue(applicationContext)
                requestQueue.add(stringRequest)
            } else {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
