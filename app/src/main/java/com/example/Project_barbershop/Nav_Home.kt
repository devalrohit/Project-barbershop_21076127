package com.example.Project_barbershop

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Nav_Home : Fragment() {

    private lateinit var button: Button
    private lateinit var button1: Button

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        button = view.findViewById(R.id.button)
        button1 = view.findViewById(R.id.button1) // inisialisasi button1

        button.setOnClickListener {
            val intentDirection = Intent(requireActivity(), booking::class.java)
            startActivity(intentDirection)
        }

        button1.setOnClickListener {
            val intentDirection = Intent(requireActivity(), capster::class.java)
            startActivity(intentDirection)
        }

        return view
    }



    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Nav_Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
