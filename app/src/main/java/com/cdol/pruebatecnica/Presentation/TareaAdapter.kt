package com.cdol.pruebatecnica.Presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.cdol.pruebatecnica.Model.Tarea
import com.cdol.pruebatecnica.R

import kotlinx.android.synthetic.main.item_tarea.view.*


class TareaAdapter(private val mContext: Context, private val listaTareas: List<Tarea>) : ArrayAdapter<Tarea>(mContext, 0, listaTareas) {

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val layout = LayoutInflater.from(mContext).inflate(R.layout.item_tarea, parent, false)

            val tarea = listaTareas[position]

            layout.id_tarea.text = (position+1).toString()
            layout.title_tarea.text = tarea.title
            layout.descrip_tarea.text= tarea.description
            layout.dateC_tarea.text = "Creada: ${tarea.dateC }"
            layout.dateF_tarea.text = "Finaliza: ${tarea.dateF }"


            return layout

        }

}