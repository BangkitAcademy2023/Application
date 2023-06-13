package com.example.proton.ui.custom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.proton.R

class CustomArrayAdapter(context: Context, items: Array<String>) :
    ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_getdropdown, parent, false)
        }

        val text = view?.findViewById<TextView>(R.id.text)
        text?.text = getItem(position)

        return view!!
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.custom_getdropdown, parent, false)
        }

        val text = view?.findViewById<TextView>(R.id.text)
        text?.text = getItem(position)

        return view!!
    }
}