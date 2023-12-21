package com.example.Project_barbershop
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class konfirmasi_booking : AppCompatActivity() {

    private lateinit var imageButton: ImageButton
    private lateinit var buttonBooking: Button
    private var urlSignIn: String = "http://192.168.100.239/my_api_android/selec_konfirmasi_boking.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasi_booking)

        val namaTextView: TextView = findViewById(R.id.nama)
        val nomorHpTextView: TextView = findViewById(R.id.nomor_hp)
        val menuTextView: TextView = findViewById(R.id.menu)
        val capsterTextView: TextView = findViewById(R.id.capster)
        val tanggalTextView: TextView = findViewById(R.id.tanggal)

        val stringRequest: StringRequest = StringRequest(
            Request.Method.POST,
            urlSignIn,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val dataObject = jsonObject.getJSONObject("data")

                    val bokingData = dataObject.getJSONObject("boking_data")
                    val bokingMenu = dataObject.getJSONObject("boking_menu")
                    val bokingMenuCapster = dataObject.getJSONObject("boking_menu_capster")
                    val bokingTanggal = dataObject.getJSONObject("boking_tanggal")

                    val nama = bokingData.getString("nama")
                    val nomorHp = bokingData.getString("nomor_hp")
                    val menu = bokingMenu.getString("menu")
                    val capster = bokingMenuCapster.getString("capster")
                    val tanggal = bokingTanggal.getString("selected_date")

                    namaTextView.text = nama
                    nomorHpTextView.text = nomorHp
                    menuTextView.text = menu
                    capsterTextView.text = capster
                    tanggalTextView.text = tanggal
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this@konfirmasi_booking, error.toString(), Toast.LENGTH_SHORT).show()
            })

        val requestQueue: RequestQueue = Volley.newRequestQueue(this@konfirmasi_booking)
        requestQueue.add(stringRequest)

        buttonBooking = findViewById(R.id.buttonBooking)
        imageButton = findViewById(R.id.imagebutton)

        buttonBooking.setOnClickListener {
            val intentDirection = Intent(this@konfirmasi_booking, MainActivity2::class.java)
            startActivity(intentDirection)
        }

        imageButton.setOnClickListener {
            val intentDirection = Intent(this@konfirmasi_booking, MainActivity2::class.java)
            startActivity(intentDirection)
        }
    }
}
