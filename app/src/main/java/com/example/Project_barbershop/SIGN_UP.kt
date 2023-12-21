package com.example.Project_barbershop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SIGN_UP : AppCompatActivity() {
    private var etUsername: EditText? = null
    private var etPassword: EditText? = null
    private var etAlamat: EditText? = null
    private var btnRegister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etAlamat = findViewById(R.id.etAlamat)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister?.setOnClickListener {
            val username = etUsername?.text.toString()
            val password = etPassword?.text.toString()
            val alamat = etAlamat?.text.toString()

            if (!(username.isEmpty() || password.isEmpty() || alamat.isEmpty())) {
                val stringRequest = object : StringRequest(
                    Request.Method.POST,
                    Db_Contract.urlRegister,
                    { response ->
                        Toast.makeText(applicationContext, response, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, SIGN_IN::class.java))
                    },
                    { error ->
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["username"] = username
                        params["password"] = password
                        params["alamat"] = alamat
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
