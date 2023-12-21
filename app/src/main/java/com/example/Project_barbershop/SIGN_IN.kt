package com.example.Project_barbershop

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SIGN_IN : AppCompatActivity() {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etUsername = findViewById(R.id.login_email)
        etPassword = findViewById(R.id.login_password)
        btnLogin = findViewById(R.id.loginButton)



        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            if (!(username.isEmpty() || password.isEmpty())) {
                val requestQueue = Volley.newRequestQueue(applicationContext)
                val stringRequest: StringRequest = object : StringRequest(
                    Method.POST, Db_Contract.urlLogin,
                    Response.Listener<String> { response ->
                        if (response == "Selamat Datang") {
                            Toast.makeText(
                                applicationContext,
                                "Login Berhasil",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(applicationContext, MainActivity2::class.java))
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Login Gagal",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(
                            applicationContext,
                            error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }) {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["username"] = username
                        params["password"] = password
                        return params
                    }
                }
                requestQueue.add(stringRequest)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Password Atau Username Salah",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
