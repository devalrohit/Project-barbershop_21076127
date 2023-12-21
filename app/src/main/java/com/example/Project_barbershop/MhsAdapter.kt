package com.example.Project_barbershop

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class MhsAdapter(private val activity: Activity, private val items: List<Data>) : BaseAdapter() {
    private var inflater: LayoutInflater? = null

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (inflater == null) {
            inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        var convertView = convertView
        if (convertView == null) {
            convertView = inflater?.inflate(R.layout.list, null)
        }

        val id = convertView?.findViewById<TextView>(R.id.id)
        val nama = convertView?.findViewById<TextView>(R.id.etUsername)
        val password = convertView?.findViewById<TextView>(R.id.etPassword)
        val alamat = convertView?.findViewById<TextView>(R.id.etAlamat)

        val data = items[position]

        id?.text = data.id.toString()
        nama?.text = data.username
        password?.text = data.password
        alamat?.text = data.alamat

        return convertView!!
    }
}
