package com.example.Project_barbershop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class capster : AppCompatActivity() {

    private lateinit var ImageButton : ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capster)
        ImageButton = findViewById(R.id.imagebutton)

        ImageButton.setOnClickListener {
            val intentDirection = Intent(this@capster,  MainActivity2::class.java)
            startActivity(intentDirection)
        }
    }
}