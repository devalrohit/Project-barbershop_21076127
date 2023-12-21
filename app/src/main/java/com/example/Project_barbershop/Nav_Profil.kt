package com.example.Project_barbershop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class Nav_Profil : Fragment() {

    private var urlSignIn: String = "http://devalrohit.my.id/my_api_android/selec.php"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val editTextUsername: TextView = view.findViewById(R.id.etUsername)
        val editTextPassword: TextView = view.findViewById(R.id.etPassword)
        val editTextAlamat: TextView = view.findViewById(R.id.etAlamat)

        val stringRequest: StringRequest = StringRequest(
            Request.Method.POST,
            urlSignIn,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val dataArray = jsonObject.getJSONArray("data")

                    // Ambil data pertama dari JSON array (jika ada)
                    if (dataArray.length() > 0) {
                        val userData = dataArray.getJSONObject(0)

                        val username = userData.getString("username")
                        val password = userData.getString("password")
                        val alamat = userData.getString("alamat")

                        editTextUsername.text = username
                        editTextPassword.text = password
                        editTextAlamat.text = alamat
                    }
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
