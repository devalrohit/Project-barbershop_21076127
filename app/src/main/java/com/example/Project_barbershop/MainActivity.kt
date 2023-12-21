package com.example.Project_barbershop


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var buttonSignIn: Button
    private lateinit var buttonSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        buttonSignIn = findViewById(R.id.btnLogin)
        buttonSignUp = findViewById(R.id.btnRegister)

        //intent untuk memindahkan halaman ketika button di klik
        buttonSignIn.setOnClickListener {
            val intentDirection = Intent(this@MainActivity, SIGN_IN::class.java)
            startActivity(intentDirection)
        }

        buttonSignUp.setOnClickListener {
            val intentDirection = Intent(this@MainActivity, SIGN_UP::class.java)
            startActivity(intentDirection)
        }
    }
}
