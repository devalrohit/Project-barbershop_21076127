package com.example.Project_barbershop

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.util.*

class tanggal : AppCompatActivity() {

    private lateinit var dateTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var confirmButton: Button
    private lateinit var requestQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanggal)

        dateTextView = findViewById(R.id.textViewDate)
        timeTextView = findViewById(R.id.textViewTime)
        datePicker = findViewById(R.id.datePicker)
        timePicker = findViewById(R.id.timePicker)
        confirmButton = findViewById(R.id.buttonConfirmBooking)
        requestQueue = Volley.newRequestQueue(this)

        dateTextView.setOnClickListener { showDatePicker() }
        timeTextView.setOnClickListener { showTimePicker() }
        confirmButton.setOnClickListener { confirmBooking() }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                dateTextView.text = "$day/${month + 1}/$year"
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                timeTextView.text = "$hourOfDay:$minute"
            },
            hour,
            minute,
            true
        )
        timePickerDialog.show()
    }

    private fun insertDataIntoDatabase(selectedDate: String, selectedTime: String) {
        val url = Db_Contract.urlBokingtanggal

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Toast.makeText(
                    this,
                    "Data untuk tanggal $selectedDate dan waktu $selectedTime dimasukkan ke dalam database",
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
                params["selected_date"] = selectedDate
                params["selected_time"] = selectedTime
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun confirmBooking() {
        val selectedDate = dateTextView.text.toString()
        val selectedTime = timeTextView.text.toString()

        insertDataIntoDatabase(selectedDate, selectedTime)
    }

    private fun moveToNextActivity() {
        val intent = Intent(
            this,
            konfirmasi_booking::class.java
        )
        startActivity(intent)
    }
}
