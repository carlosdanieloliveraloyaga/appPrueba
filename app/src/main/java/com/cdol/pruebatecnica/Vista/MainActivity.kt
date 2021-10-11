package com.cdol.pruebatecnica.Vista

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.cdol.pruebatecnica.Model.AppDatabase
import com.cdol.pruebatecnica.Model.Tarea
import com.cdol.pruebatecnica.Presentation.AgregarTarea
import com.cdol.pruebatecnica.Presentation.DatePickerFragment
import com.cdol.pruebatecnica.Presentation.TareaAdapter
import com.cdol.pruebatecnica.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_agregar_tarea.view.*
import kotlinx.android.synthetic.main.item_tarea.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.KeyStore.Builder.newInstance
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val idTarea:Int?= null
        var listaTareas =ArrayList<Tarea>()
        var adapter =TareaAdapter(this, listaTareas)
        val database = AppDatabase.getDatabase(this)

        database.tareas().getAll().observe(this, Observer {
            listaTareas = it as ArrayList<Tarea>
            adapter = TareaAdapter(this,listaTareas)
            lista.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, TareaActivity::class.java)
            intent.putExtra("id", listaTareas[position].idTarea)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {



            val dialog = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_tarea,null)
            val mBuilder = AlertDialog.Builder(this).setView(dialog)
            val mAlertDialog=mBuilder.show()
            dialog!!.fechaFInputEditText.setOnClickListener {

                val datePicker = DatePickerFragment { day, month, year ->
                    val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
                    dialog.fechaFInputEditText.setText(selectedDate)
                }
                datePicker.show(supportFragmentManager, "datePicker")


            }
            dialog.btnaceptar.setOnClickListener {


                    //val tarea = intent.extras?.getSerializable("tarea") as Tarea
                    val title = dialog.titleInputEditText.text.toString()
                    val contenido = dialog.contenidoInputEditText.text.toString()
                    val fechaF = dialog.fechaFInputEditText.text.toString()

                    val sdf = SimpleDateFormat("dd/MM/yyyy HH'h'mm")
                    val fechaC = sdf.format(Date())
                Toast.makeText(this@MainActivity,"${fechaF} ",Toast.LENGTH_LONG).show()

                    val tarea = Tarea(title,contenido,fechaC,fechaF)

                if (idTarea != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        tarea.idTarea = idTarea

                        database.tareas().update(tarea)
                        mAlertDialog.dismiss()


                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        listaTareas.add(tarea)
                        adapter.notifyDataSetChanged()
                        database.tareas().insertAll(tarea)


                        Toast.makeText(this@MainActivity,"${tarea.idTarea} ${tarea.dateC}",Toast.LENGTH_LONG).show()
                        mAlertDialog.dismiss()

                    }
                }
            }
            dialog.btncancelar.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }






        }


    }


