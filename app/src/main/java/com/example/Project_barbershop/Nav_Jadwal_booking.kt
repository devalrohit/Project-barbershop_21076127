package com.example.Project_barbershop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class Nav_Jadwal_booking : Fragment() {


    private var urlSignIn: String = "http://192.168.100.239/my_api_android/selec_konfirmasi_boking.php"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jadwal_booking, container, false)

        val namaTextView: TextView = view.findViewById(R.id.nama)
        val nomorHpTextView: TextView = view.findViewById(R.id.nomor_hp)
        val menuTextView: TextView = view.findViewById(R.id.menu)
        val capsterTextView: TextView = view.findViewById(R.id.capster)
        val tanggalTextView: TextView = view.findViewById(R.id.tanggal)

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
                Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
            })

        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(stringRequest)



        return view
    }
}
