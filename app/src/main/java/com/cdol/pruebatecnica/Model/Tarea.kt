package com.cdol.pruebatecnica.Model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable




    @Entity(tableName = "tareas")
    class Tarea(
        val title:String,
        val description: String,
        val dateC: String,
        val dateF:String,
        @PrimaryKey(autoGenerate = true)
        var idTarea: Int = 0
    ) : Serializable



