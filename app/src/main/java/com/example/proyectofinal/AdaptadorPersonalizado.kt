package com.example.proyectofinal

import Sesion.Sesion
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class AdaptadorPersonalizado : ArrayAdapter<Sesion> {
    private lateinit var sesiones: List<Sesion>
    constructor(context: Context, lista: List<Sesion>)
        :super(context, R.layout.item, lista) {
            sesiones = lista
        }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val convertView = inflater.inflate(R.layout.item, null)

        val textHora = convertView.findViewById<TextView>(R.id.hora)

        textHora.text = (sesiones[position].hora.toString())

        return convertView
    }


}