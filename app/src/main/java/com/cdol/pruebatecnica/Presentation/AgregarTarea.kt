package com.cdol.pruebatecnica.Presentation

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import com.cdol.pruebatecnica.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.dialog_agregar_tarea.view.*

class AgregarTarea(context: Context) {


    val dialog= LayoutInflater.from(context).inflate(R.layout.dialog_agregar_tarea,null)
    val mBuilder = AlertDialog.Builder(context).setView(dialog)
    val mAlertDialog=mBuilder.show()



}